package dyrax.klingon.Data;


import java.util.ArrayList;

public interface IDifficultyCreator {

    ArrayList<Difficulty> createDifficulties(int level, int currentChars, int newChars, int maxChars, Language language);

    int newChars(int level, int currentChars, int newChars, int maxChars, Language language);
}
