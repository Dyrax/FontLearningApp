package dyrax.klingon.Data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class LangProgress {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @Ignore
    private Language language;
    private String languageId;
    private int level;
    private ArrayList<String> currentAlphabet;
    private ArrayList<String> newestChars;
    @Ignore
    private ArrayList<DifficultyProgress> difficulties;
    private ArrayList<Long> difficultyIds;

    public LangProgress(String languageId, int level, ArrayList<String> currentAlphabet, ArrayList<String> newestChars, ArrayList<Long> difficultyIds) {
        this.languageId = languageId;
        this.level = level;
        this.currentAlphabet = currentAlphabet;
        this.newestChars = newestChars;
        this.difficultyIds = difficultyIds;

        this.language = Languages.fromName(this.languageId);
        this.difficulties = new ArrayList<>();
        ArrayList<Difficulty> createdDifficulties = this.language.getDifficultyCreator().createDifficulties(
                this.level, this.currentAlphabet.size(), this.newestChars.size(), this.language.getAlphabet().size(), this.language);
        for (long id : difficultyIds) {
            DifficultyProgress diff = LanguageDatabase.getInstance().languageDAO().loadDifficultyProgress(id);
            diff.setDifficulty(createdDifficulties.get(diff.getDifficultyIndex()));
            this.difficulties.add(diff);
        }

        updateLocks();
    }

    private LangProgress(String languageId) {
        this.language = Languages.fromName(languageId);
        this.languageId = languageId;
        this.level = 1;
        this.currentAlphabet = new ArrayList<>(this.language.getAlphabet().subList(0, 4));
        this.newestChars = new ArrayList<>(this.language.getAlphabet().subList(0, 4));
        this.difficulties = new ArrayList<>();
        int i = 0;
        for (Difficulty d : this.language.getDifficultyCreator().createDifficulties(this.level,
                this.currentAlphabet.size(), this.newestChars.size(), this.language.getAlphabet().size(), this.language)) {
            this.difficulties.add(DifficultyProgress.createNew(d, i++));
        }
        this.difficultyIds = new ArrayList<>();
        for (DifficultyProgress d : this.difficulties) {
            this.difficultyIds.add(d.id);
        }

        this.id = LanguageDatabase.getInstance().languageDAO().insertLangProgress(this);

        updateLocks();
    }

    public static LangProgress CreateNew(String languageId) {
        return new LangProgress(languageId);
    }

    public String getLanguageId() {
        return languageId;
    }

    public ArrayList<Long> getDifficultyIds() {
        return difficultyIds;
    }

    public Language getLanguage() {
        return language;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getCurrentAlphabet() {
        return currentAlphabet;
    }

    public ArrayList<String> getNewestChars() {
        return newestChars;
    }

    public ArrayList<DifficultyProgress> getDifficulties() {
        return difficulties;
    }

    public void updateLocks() {
        for(int i = 0; i < this.difficulties.size(); i++) {
            DifficultyProgress difficultyProgress = this.difficulties.get(i);
            float streak = difficultyProgress.getDifficulty().getUnlockStreak();
            float total = difficultyProgress.getDifficulty().getUnlockTotal();
            if(streak < 0.0f || total < 0.0f) {
                difficultyProgress.setLocked(false);
            } else {
                DifficultyProgress previous = this.difficulties.get(i-1);
                float prevStreak = (float)previous.getCurrentStreak() / (float)previous.getDifficulty().getNeededStreak();
                float prevTotal = (float)previous.getCurrentTotal() / (float)previous.getDifficulty().getNeededTotal();
                difficultyProgress.setLocked(streak > prevStreak || total > prevTotal);
            }
        }
    }
}
