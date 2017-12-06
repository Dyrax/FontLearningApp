package dyrax.klingon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import dyrax.klingon.Data.DifficultyProgress;
import dyrax.klingon.Global;
import dyrax.klingon.R;

public class TrainingActivity extends AppCompatActivity /*implements Alphabet.ClickListener*/ {
    public static final String EXTRA_ALPHABET = "dyrax.klingon.extra.ALPHABET";

    DifficultyProgress difficultyProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        this.difficultyProgress = Global.getDifficultyProgress();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        //Alphabet alphabet = Alphabet.ALPHABETS[intent.getIntExtra(EXTRA_ALPHABET, 0)];
        //alphabet.randomize();

        //LinearLayout keyboard = alphabet.createKeyboard(this, this);

        ConstraintLayout root = findViewById(R.id.root);
        //root.addView(keyboard);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(root);

        //constraintSet.connect(keyboard.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 50);
        //constraintSet.constrainDefaultHeight(keyboard.getId(), 200);
        constraintSet.applyTo(root);

    }

    //@Override
    public void alphabetClick(Button bttn, char character) {
        TextView text = findViewById(R.id.textView3);
        text.setText(Character.toString(character));
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
