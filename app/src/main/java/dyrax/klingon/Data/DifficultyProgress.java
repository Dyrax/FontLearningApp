package dyrax.klingon.Data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DifficultyProgress {
    @PrimaryKey(autoGenerate = true)
    public long id;

    private int currentStreak;
    private int currentTotal;
    private int highestStreak;

    private boolean locked;
    private float unLockProgress;

    @Ignore
    private Difficulty difficulty;
    private int difficultyIndex;

    public DifficultyProgress(int currentStreak, int currentTotal,
                              int highestStreak, boolean locked, float unLockProgress, int difficultyIndex) {
        this.currentStreak = currentStreak;
        this.currentTotal = currentTotal;
        this.highestStreak = highestStreak;
        this.locked = locked;
        this.difficultyIndex = difficultyIndex;
        this.difficulty = null;
        this.unLockProgress = unLockProgress;
    }

    public static DifficultyProgress createNew(Difficulty difficulty, int difficultyIndex) {
        DifficultyProgress result = new DifficultyProgress(0, 0,
                0, true, 0.0f, difficultyIndex);
        result.setDifficulty(difficulty);
        result.id = LanguageDatabase.getInstance().languageDAO().insertDifficultyProgress(result);
        return result;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public int getHighestStreak() {
        return highestStreak;
    }

    public void setLocked(boolean locked, float streak, float total) {
        streak = Math.max(0, Math.min(1, streak));
        total = Math.max(0, Math.min(1, total));
        this.locked = locked;
        this.unLockProgress = (streak + total) / 2;
    }

    public float getUnLockProgress() {
        return unLockProgress;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getDifficultyIndex() {
        return difficultyIndex;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void correctClick() {
        this.currentTotal += 1;
        this.currentStreak += 1;
        this.highestStreak = Math.max(this.highestStreak, this.currentStreak);
    }

    public void wrongClick() {
        this.currentStreak = 0;
    }

    public boolean isCompleted() {
        return highestStreak >= difficulty.getNeededStreak() && currentTotal >= difficulty.getNeededTotal();
    }
}

