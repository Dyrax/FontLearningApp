package dyrax.klingon.Data;


import java.util.ArrayList;

import dyrax.klingon.Data.Translation.Direction;

public class Difficulty {

    private int neededTotal;
    private int neededStreak;

    private ArrayList<Translation> translations;

    private Direction direction;

    private int maxTime;

    public enum Mode {
        WORDS,
        NEW_ONLY,
        ALL
    }
    private Mode mode;

    private float unlockTotal;
    private float unlockStreak;

    private Keyboard keyboard;


    public Difficulty(int neededTotal, int neededStreak, ArrayList<Translation> translations, Direction direction, int maxTime, Mode mode, float unlockTotal, float unlockStreak, Keyboard keyboard) {
        this.neededTotal = neededTotal;
        this.neededStreak = neededStreak;
        this.translations = translations;
        this.direction = direction;
        this.maxTime = maxTime;
        this.mode = mode;
        this.unlockStreak = unlockStreak;
        this.unlockTotal = unlockTotal;
        this.keyboard = keyboard;
    }

    public int getNeededTotal() {
        return neededTotal;
    }

    public int getNeededStreak() {
        return neededStreak;
    }

    public ArrayList<Translation> getTranslations() {
        return translations;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public Mode getMode() {
        return mode;
    }

    public float getUnlockTotal() {

        return unlockTotal;
    }

    public float getUnlockStreak() {
        return unlockStreak;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }
}
