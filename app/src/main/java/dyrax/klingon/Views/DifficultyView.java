package dyrax.klingon.Views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.TextView;

import dyrax.klingon.Data.DifficultyProgress;
import dyrax.klingon.R;

public class DifficultyView extends ConstraintLayout {

    DifficultyProgress difficulty;

    public DifficultyView(Context context, DifficultyProgress difficulty) {
        super(context);
        init(context, difficulty);
    }

    private void init(Context context, DifficultyProgress difficulty) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_difficulty, this);

        this.difficulty = difficulty;
        update();
    }

    public void unlock() {
        findViewById(R.id.lockImage).setVisibility(INVISIBLE);
        findViewById(R.id.layout).setVisibility(VISIBLE);
    }


    public void update() {
        TextView currentStreak = findViewById(R.id.currentStreak);
        currentStreak.setText(getContext().getString(R.string.current_streak, difficulty.getCurrentStreak()));

        TextView highestStreak = findViewById(R.id.highestStreak);
        highestStreak.setText(getContext().getString(R.string.highest_streak, difficulty.getHighestStreak()));

        TextView total = findViewById(R.id.total);
        total.setText(getContext().getString(R.string.total, difficulty.getCurrentTotal()));

        if(!difficulty.isLocked()) {
            unlock();
        }
    }

    public DifficultyProgress getDifficulty() {
        return difficulty;
    }
}
