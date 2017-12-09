package dyrax.klingon.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.List;
import java.util.StringJoiner;

import dyrax.klingon.Data.DifficultyProgress;
import dyrax.klingon.Data.LangProgress;
import dyrax.klingon.Data.LanguageDatabase;
import dyrax.klingon.Global;
import dyrax.klingon.R;
import dyrax.klingon.Views.DifficultyView;

public class ProgressActivity extends AppCompatActivity implements OnClickListener {

    public static final String EXTRA_LANG_ID = "dyrax_klingon_language_id";

    private static class FillContentTask extends AsyncTask<Long, Void, LangProgress> {

        private static String convertArray(List<String> alphabet) {
            StringJoiner joiner = new StringJoiner(", ");
            for (String character : alphabet) {
                joiner.add(character);
            }
            return joiner.toString();
        }

        ProgressActivity activity;

        FillContentTask(ProgressActivity activity) {
            this.activity = activity;
        }

        protected LangProgress doInBackground(Long... params) {
            long langId = params[0];
            LangProgress progress = LanguageDatabase.getInstance().languageDAO().loadLangProgress(langId);
            progress.upgrade();
            return progress;
        }

        protected void onPostExecute(LangProgress progress) {
            activity.langProgress = progress;
            activity.setTitle(progress.getLanguage().getName());

            TextView level = activity.findViewById(R.id.level);
            level.setText(activity.getString(R.string.level, progress.getLevel()));

            TextView currentChars = activity.findViewById(R.id.currentChars);
            currentChars.setText(convertArray(progress.getCurrentAlphabet()));
            currentChars.setTypeface(progress.getLanguage().getFont());

            TextView newChars = activity.findViewById(R.id.newChars);
            newChars.setText(convertArray(progress.getNewestChars()));
            newChars.setTypeface(progress.getLanguage().getFont());

            LinearLayout diffLayout = activity.findViewById(R.id.difficulties);
            diffLayout.removeAllViews();
            diffLayout.addView(new Space(activity));
            for (DifficultyProgress diff : progress.getDifficulties()) {
                DifficultyView difficultyView = new DifficultyView(activity, diff);
                difficultyView.setOnClickListener(activity);
                diffLayout.addView(difficultyView);
            }
            diffLayout.addView(new Space(activity));
        }
    }

    LangProgress langProgress;
    long langId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        Intent intent = getIntent();
        langId = intent.getLongExtra(EXTRA_LANG_ID, -1);
        if(langId == -1) {
            throw new RuntimeException("Progressactivity no extra lang_id");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new FillContentTask(this).execute(langId);
    }

    @Override
    public void onClick(View view) {
        DifficultyView difficultyView = (DifficultyView) view;
        Global.setDifficultyProgress(difficultyView.getDifficulty());
        Global.setLangProgress(this.langProgress);
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
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
}
