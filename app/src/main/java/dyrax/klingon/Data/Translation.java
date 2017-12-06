package dyrax.klingon.Data;

import android.graphics.Typeface;

import java.util.ArrayList;

public class Translation {
    public enum Direction {
        TO_THIS,
        FROM_THIS,
        BOTH_DIRS;

        public boolean isToThis() {
            return this == TO_THIS || this == BOTH_DIRS;
        }
        public boolean isFromThis() {
            return this == FROM_THIS || this == BOTH_DIRS;
        }
        public boolean isOkayWith(Direction dir) {
            switch(this) {
                case TO_THIS:
                    return dir.isToThis();
                case FROM_THIS:
                    return dir.isFromThis();
                default:
                    return true;
            }
        }
    }

    private ArrayList<String> alphabet;
    private Direction direction;
    private Typeface font;

    public Translation(ArrayList<String> alphabet, Direction direction, Typeface font) {
        this.alphabet = alphabet;
        this.direction = direction;
        this.font = font;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public Direction getDirection() {
        return direction;
    }

    public String translateToThis(String original) {
        return original;
    }
    public String translateFromThis(String original) {
        return original;
    }

    public Typeface getFont() {
        return font;
    }
}
