package dyrax.klingon.Activities;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import dyrax.klingon.Data.DifficultyProgress;
import dyrax.klingon.Data.Keyboard.ClickListener;
import dyrax.klingon.Data.LangProgress;
import dyrax.klingon.Data.Language;
import dyrax.klingon.Data.LanguageDatabase;
import dyrax.klingon.Data.Translation;
import dyrax.klingon.Data.Translation.Direction;
import dyrax.klingon.Global;
import dyrax.klingon.R;
import dyrax.klingon.Views.CircularTimerView;
import dyrax.klingon.Views.CircularTimerView.TimerListener;

public class TrainingActivity extends AppCompatActivity implements ClickListener, OnClickListener, TimerListener {
    public static final String EXTRA_ALPHABET = "dyrax.klingon.extra.ALPHABET";

    private static class UpdateDifficultyTask extends AsyncTask<TrainingActivity, Void, Void> {

        protected Void doInBackground(TrainingActivity... params) {
            TrainingActivity activity = params[0];
            LanguageDatabase.getInstance().languageDAO().updateDifficultyProgress(activity.difficultyProgress);
            return null;
        }
    }


    DifficultyProgress difficultyProgress;
    LangProgress langProgress;
    LinearLayout keyboard;
    LinearLayout markedKeyboard;
    String searchedChar;
    Typeface fontSearched;
    Typeface fontKeyboard;
    Translation translation;
    Direction direction;
    boolean stopped = false;

    HashMap<Translation, ArrayList<String>> translationLists = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        this.difficultyProgress = Global.getDifficultyProgress();
        this.langProgress = Global.getLangProgress();

        this.setTitle(getString(R.string.training, langProgress.getLanguage().getName()));

        for(Translation t : langProgress.getLanguage().getTranslations()) {
            ArrayList<String> list = new ArrayList<>();
            for (String s : langProgress.getCurrentAlphabet()) {
                list.add(t.translateFromThis(s));
            }
            translationLists.put(t, list);
        }

        findViewById(R.id.root).setOnClickListener(this);

        if(difficultyProgress.getDifficulty().getMaxTime() <= 0)
            findViewById(R.id.timer).setVisibility(View.GONE);

        update();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void keyboardClick(Button bttn, String character) {
        ((CircularTimerView)(findViewById(R.id.timer))).stop();
        final TextView textMsg;
        if (direction == Direction.FROM_THIS ?
                translation.translateFromThis(searchedChar).equals(character) :
                translation.translateToThis(searchedChar).equals(character)) {
            rightOutcome();
            textMsg = findViewById(R.id.textCorrect);
        } else {
            wrongOutcome();
            textMsg = findViewById(R.id.textWrong);
        }

        showMessage(textMsg);
        new UpdateDifficultyTask().execute(this);
    }

    private void rightOutcome() {
        stopped = false;
        difficultyProgress.correctClick();
        update();
    }

    private void wrongOutcome() {
        stopped = true;
        difficultyProgress.wrongClick();
        ConstraintLayout root = findViewById(R.id.root);
        root.removeView(keyboard);
        root.addView(markedKeyboard);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(root);

        constraintSet.connect(markedKeyboard.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 50);
        constraintSet.constrainDefaultHeight(markedKeyboard.getId(), 200);
        constraintSet.applyTo(root);

        updateText();
    }

    private void showMessage(final TextView textMsg) {
        findViewById(R.id.textCorrect).setVisibility(View.GONE);
        findViewById(R.id.textWrong).setVisibility(View.GONE);
        findViewById(R.id.textTimeOver).setVisibility(View.GONE);

        textMsg.setVisibility(View.VISIBLE);
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(200);
        fadeOut.setDuration(1000);
        fadeOut.setAnimationListener(new AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                textMsg.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        textMsg.setAnimation(fadeOut);
    }

    private void update() {
        Language language = langProgress.getLanguage();
        int translationCount = language.getTranslations().size();
        int translationIndex = ThreadLocalRandom.current().nextInt(translationCount);
        translation = language.getTranslations().get(translationIndex);
        direction = difficultyProgress.getDifficulty().getDirection();
        if (direction == Direction.BOTH_DIRS) {
            direction = ThreadLocalRandom.current().nextBoolean() ? Direction.FROM_THIS : Direction.TO_THIS;
        }
        int charIndex = ThreadLocalRandom.current().nextInt(langProgress.getCurrentAlphabet().size());
        float sizeFactor, searchedFactor;
        if (direction == Direction.FROM_THIS) {
            searchedChar = langProgress.getCurrentAlphabet().get(charIndex);
            difficultyProgress.getDifficulty().getKeyboard().insert(translationLists.get(translation), translation.translateFromThis(searchedChar));
            fontSearched = language.getFont();
            fontKeyboard = translation.getFont();
            sizeFactor = translation.getSizeFactor();
            searchedFactor = language.getSizeFactor();
        } else {
            String original = langProgress.getCurrentAlphabet().get(charIndex);
            searchedChar = translation.translateFromThis(original);
            difficultyProgress.getDifficulty().getKeyboard().insert(langProgress.getCurrentAlphabet(), langProgress.getCurrentAlphabet().get(charIndex));
            fontSearched = translation.getFont();
            fontKeyboard = language.getFont();
            sizeFactor = language.getSizeFactor();
            searchedFactor = translation.getSizeFactor();
        }

        ConstraintLayout root = findViewById(R.id.root);
        root.removeView(keyboard);
        root.removeView(markedKeyboard);
        this.keyboard = difficultyProgress.getDifficulty().getKeyboard().createKeyboard(
                this, this, fontKeyboard, sizeFactor);
        this.markedKeyboard = difficultyProgress.getDifficulty().getKeyboard().createMarkedKeyboard(
                this, fontKeyboard, sizeFactor);
        root.addView(keyboard);


        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(root);

        constraintSet.connect(keyboard.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 50);
        constraintSet.constrainDefaultHeight(keyboard.getId(), 200);
        constraintSet.applyTo(root);

        if(difficultyProgress.getDifficulty().getMaxTime() > 0) {
            CircularTimerView timer = findViewById(R.id.timer);
            timer.start(5000, this);
        }

        TextView text = findViewById(R.id.textView3);
        text.setText(searchedChar);
        text.setTypeface(fontSearched);
        text.setTextSize(searchedFactor * 100.0f);
        updateText();
    }
    private void updateText() {
        TextView currentStreak = findViewById(R.id.currentStreak);
        currentStreak.setText(getString(R.string.current_streak, difficultyProgress.getCurrentStreak()));

        TextView highestStreak = findViewById(R.id.highestStreak);
        highestStreak.setText(getString(R.string.highest_streak, difficultyProgress.getHighestStreak()));

        TextView total = findViewById(R.id.total);
        total.setText(getString(R.string.total, difficultyProgress.getCurrentTotal()));
    }

    @Override
    public void onClick(View view) {
        if(stopped) {
            stopped = false;
            update();
        }
    }

    @Override
    public void onTimerCompleted() {
        wrongOutcome();
        showMessage((TextView) findViewById(R.id.textTimeOver));
        new UpdateDifficultyTask().execute(this);
    }
}
