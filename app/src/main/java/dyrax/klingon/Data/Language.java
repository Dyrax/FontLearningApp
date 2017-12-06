package dyrax.klingon.Data;


import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

public class Language {

    private List<String> alphabet;
    private ArrayList<Translation> translations;
    private Typeface font;
    private String name;

    private IDifficultyCreator difficultyCreator;

    public Language(String name, List<String> alphabet, Typeface font,
                    ArrayList<Translation> translations, IDifficultyCreator difficultyCreator) {
        this.alphabet = alphabet;
        this.translations = translations;
        this.font = font;
        this.name = name;
        this.difficultyCreator = difficultyCreator;
    }

    public IDifficultyCreator getDifficultyCreator() {
        return difficultyCreator;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public ArrayList<Translation> getTranslations() {
        return translations;
    }

    public Typeface getFont() {
        return font;
    }

    public String getName() {
        return this.name;
    }

}
