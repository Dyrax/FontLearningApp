package dyrax.klingon.Data;

import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.HashMap;

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
        public boolean contains(Direction dir) {
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
    private HashMap<String, String> mapFromThis; // From Language to Translation
    private HashMap<String, String> mapToThis;   // From Translation to Language
    private float sizeFactor;

    public Translation(ArrayList<String> alphabet, Direction direction, HashMap<String, String> translateMap, Typeface font, float sizeFactor) {
        this.alphabet = alphabet;
        this.direction = direction;
        this.font = font;
        if(direction == Direction.FROM_THIS || direction == Direction.BOTH_DIRS)
            this.mapFromThis = translateMap;
        else
            this.mapToThis = translateMap;

        if(direction == Direction.BOTH_DIRS) {
            this.mapToThis = new HashMap<>();
            for (String key : translateMap.keySet()) {
                this.mapToThis.put(translateMap.get(key), key);
            }
        }
        this.sizeFactor = sizeFactor;
    }

    public float getSizeFactor() {
        return sizeFactor;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public Direction getDirection() {
        return direction;
    }

    public String translateToThis(String original) {
        return mapToThis.get(original);
    }
    public String translateFromThis(String original) {
        return mapFromThis.get(original);
    }

    public Typeface getFont() {
        return font;
    }
}
