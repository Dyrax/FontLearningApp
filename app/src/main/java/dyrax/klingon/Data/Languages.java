package dyrax.klingon.Data;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dyrax.klingon.Data.Translation.Direction;

public class Languages {

    private static final String[] LATIN_ALPHABET = new String[] {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private static Language KLINGON(AssetManager assetManager) {
        ArrayList<Translation> translations = new ArrayList<>(Arrays.asList(
                new Translation(
                        new ArrayList<>(Arrays.asList(LATIN_ALPHABET)),
                        Direction.BOTH_DIRS,
                        Typeface.create("sans-serif", Typeface.NORMAL)
                )
        ));
        return  new Language(
                "Klingon",
                Arrays.asList(LATIN_ALPHABET),
                Typeface.createFromAsset(assetManager, "fonts/klingon.ttf"),
                translations,
                new StandardDifficultyCreator()
        );
    }

    private static Language GREEK(AssetManager assetManager) {
        ArrayList<Translation> translations = new ArrayList<>(Arrays.asList(
                new Translation(
                        new ArrayList<>(Arrays.asList(LATIN_ALPHABET)),
                        Direction.BOTH_DIRS,
                        Typeface.create("sans-serif", Typeface.NORMAL)
                )
        ));
        return  new Language(
                "Greek",
                Arrays.asList("α", "β", "γ", "δ", "ε", "ζ"),
                Typeface.create("sans-serif", Typeface.NORMAL),
                translations,
                new StandardDifficultyCreator()
        );
    }


    public static Language fromName(String name) {
        for (Language l : ALL_LANGUAGES) {
            if(l.getName().equals(name)) {
                return l;
            }
        }
        throw new IllegalArgumentException("Language with name \"" + name + "\" not found");
    }

    public static List<Language> ALL_LANGUAGES;

    public static void initialize(AssetManager assetManager) {
        ALL_LANGUAGES = Arrays.asList (
                KLINGON(assetManager),
                GREEK(assetManager)
        );
    }
}
