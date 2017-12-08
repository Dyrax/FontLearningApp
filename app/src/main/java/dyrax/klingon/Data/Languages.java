package dyrax.klingon.Data;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dyrax.klingon.Data.Keyboard.Row;
import dyrax.klingon.Data.Translation.Direction;

public class Languages {

    private static final String[] LATIN_ALPHABET = new String[] {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private static final HashMap<String,String> MAP_LATIN_TO_LATIN = new HashMap<String, String>() {
        {
            put("A","A");
            put("B","B");
            put("C","C");
            put("D","D");
            put("E","E");
            put("F","F");
            put("G","G");
            put("H","H");
            put("I","I");
            put("J","J");
            put("K","K");
            put("L","L");
            put("M","M");
            put("N","N");
            put("O","O");
            put("P","P");
            put("Q","Q");
            put("R","R");
            put("S","S");
            put("T","T");
            put("U","U");
            put("V","V");
            put("W","W");
            put("X","X");
            put("Y","Y");
            put("Z","Z");
        }
    };

    private static final HashMap<String,String> MAP_GREEK_TO_LATIN = new HashMap<String, String>() {
        {
            put("Α","up. Alpha");
            put("Β","up. Beta");
            put("Γ","up. Gamma");
            put("Δ","up. Delta");
            put("Ε","up. Epsilon");
            put("Ζ","up. Zeta");
            put("Η","up. Eta");
            put("Θ","up. Theta");
            put("Ι","up. Iota");
            put("Κ","up. Kappa");
            put("Λ","up. Lambda");
            put("Μ","up. My");
            put("Ν","up. Ny");
            put("Ξ","up. Xi");
            put("Ο","up. Omikron");
            put("Π","up. Pi");
            put("Ρ","up. Rho");
            put("Σ","up. Sigma");
            put("Τ","up. Tau");
            put("Υ","up. Ypsilon");
            put("Φ","up. Phi");
            put("Χ","up. Chi");
            put("Ψ","up. Psi");
            put("Ω","up. Omega");

            put("α","lo. Alpha");
            put("β","lo. Beta");
            put("γ","lo. Gamma");
            put("δ","lo. Delta");
            put("ϵ","lo. Epsilon");
            put("ζ","lo. Zeta");
            put("η","lo. Eta");
            put("θ","lo. Theta");
            put("ι","lo. Iota");
            put("κ","lo. Kappa");
            put("λ","lo. Lambda");
            put("μ","lo. My");
            put("ν","lo. Ny");
            put("ξ","lo. Xi");
            put("ο","lo. Omikron");
            put("π","lo. Pi");
            put("ρ","lo. Rho");
            put("σ","lo. Sigma");
            put("τ","lo. Tau");
            put("υ","lo. Ypsilon");
            put("ϕ","lo. Phi");
            put("χ","lo. Chi");
            put("ψ","lo. Psi");
            put("ω","lo. Omega");
        }
    };

    private static final String[] GREEK_ALPHABET = new String[] {
            "up. Alpha","up. Beta","up. Gamma","up. Delta","up. Epsilon","up. Zeta","up. Eta",
            "up. Theta","up. Iota","up. Kappa","up. Lambda","up. My","up. Ny","up. Xi","up. Omikron",
            "up. Pi","up. Rho","up. Sigma","up. Tau","up. Ypsilon","up. Phi","up. Chi","up. Psi",
            "up. Omega","lo. Alpha","lo. Beta","lo. Gamma","lo. Delta","lo. Epsilon","lo. Zeta",
            "lo. Eta","lo. Theta","lo. Iota","lo. Kappa","lo. Lambda","lo. My","lo. Ny","lo. Xi",
            "lo. Omikron","lo. Pi","lo. Rho","lo. Sigma","lo. Tau","lo. Ypsilon","lo. Phi","lo. Chi",
            "lo. Psi","lo. Omega"
    };

    private static final String[] GREEK_NAMES_ALPHABET = new String[] {
            "Α", "Β", "Γ", "Δ", "Ε", "Ζ", "Η", "Θ", "Ι", "Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Π", "Ρ", "Σ",
            "Τ", "Υ", "Φ", "Χ", "Ψ", "Ω", "α", "β", "γ", "δ", "ϵ", "ζ", "η", "θ", "ι", "κ", "λ", "μ",
            "ν", "ξ", "ο", "π", "ρ", "σ", "τ", "υ", "ϕ", "χ", "ψ", "ω"
    };

    private static Keyboard KEYBOARD_GREEK = new Keyboard(new Row[] {
            new Row(0.0f, 8, 0.0f, new String[] {
            "Α", "Β", "Γ", "Δ", "Ε", "Ζ", "Η", "Θ"
            }),
            new Row(0.0f, 8, 0.0f, new String[] {
            "Ι", "Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Π"
            }),
            new Row(0.0f, 8, 0.0f, new String[] {
            "Ρ", "Σ", "Τ", "Υ", "Φ", "Χ", "Ψ", "Ω"
            }),
            new Row(0.0f, 8, 0.0f, new String[] {
            "α", "β", "γ", "δ", "ϵ", "ζ", "η", "θ"
            }),
            new Row(0.0f, 8, 0.0f, new String[] {
            "ι", "κ", "λ", "μ", "ν", "ξ", "ο", "π"
            }),
            new Row(0.0f, 8, 0.0f, new String[] {
            "ρ", "σ", "τ", "υ", "ϕ", "χ", "ψ", "ω"
            })},
            20, false
    );

    private static Language KLINGON(AssetManager assetManager) {
        ArrayList<Translation> translations = new ArrayList<>(Arrays.asList(
                new Translation(
                        new ArrayList<>(Arrays.asList(LATIN_ALPHABET)),
                        Direction.BOTH_DIRS,
                        MAP_LATIN_TO_LATIN,
                        Typeface.create("sans-serif", Typeface.NORMAL),
                        1.0f
                )
        ));
        return  new Language(
                "Klingon",
                Arrays.asList(LATIN_ALPHABET),
                Typeface.createFromAsset(assetManager, "fonts/klingon.ttf"),
                1.0f,
                translations,
                new StandardDifficultyCreator()
        );
    }

    private static Language GREEK(AssetManager assetManager) {
        ArrayList<Translation> translations = new ArrayList<>(Arrays.asList(
                new Translation(
                        new ArrayList<>(Arrays.asList(GREEK_NAMES_ALPHABET)),
                        Direction.BOTH_DIRS,
                        MAP_GREEK_TO_LATIN,
                        Typeface.create("sans-serif", Typeface.NORMAL),
                        0.5f
                )
        ));
        return  new Language(
                "Greek",
                new ArrayList<>(Arrays.asList(GREEK_ALPHABET)),
                Typeface.create("sans-serif", Typeface.NORMAL),
                1.0f,
                translations,
                new StandardDifficultyCreator(KEYBOARD_GREEK, Direction.TO_THIS)
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
