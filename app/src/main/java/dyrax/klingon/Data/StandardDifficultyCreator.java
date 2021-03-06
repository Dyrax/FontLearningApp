package dyrax.klingon.Data;


import java.util.ArrayList;

import dyrax.klingon.Data.Difficulty.Mode;
import dyrax.klingon.Data.Keyboard.Row;
import dyrax.klingon.Data.Translation.Direction;

public class StandardDifficultyCreator implements IDifficultyCreator{

    private static Keyboard keyboard4 = new Keyboard(new Row[] {
            new Row(0.2f, 2, 0.2f),
            new Row(0.2f, 2, 0.2f)},
            60, true);

    private static Keyboard keyboard6 = new Keyboard(new Row[] {
            new Row(0.3f, 3, 0.3f),
            new Row(0.3f, 3, 0.3f)},
            60, true);

    private static Keyboard default_keyboardAll = new Keyboard(new Row[] {
            new Row(0.0f, 10, 0.0f, new String[] {
                    "Q", "W","E","R","T","Z","U","I","O","P"
            }),
            new Row(0.5f, 9, 0.5f, new String[] {
                    "A", "S","D","F","G","H","J","K","L"
            }),
            new Row(1.5f, 7, 1.5f, new String[] {
                    "Y", "X","C","V","B","N","M"
            })},
            10, false);

    private Keyboard keyboardAll;
    private Direction dirAll;

    public StandardDifficultyCreator(Keyboard keyboardAll, Direction dirAll) {
        this.keyboardAll = keyboardAll;
        this.dirAll = dirAll;
    }

    public StandardDifficultyCreator() {
        this(default_keyboardAll, Direction.BOTH_DIRS);
    }

    @Override
    public ArrayList<Difficulty> createDifficulties(int level, int currentChars, int newChars, int maxChars, Language language) {
        ArrayList<Difficulty> difficulties = new ArrayList<>();

        if(newChars > 0) {
            difficulties.add(new Difficulty(
                            15,
                            4,
                            language.getTranslations(),
                            Direction.BOTH_DIRS,
                            -1,
                            Mode.NEW_ONLY,
                            -1, -1,
                            keyboard4
                    )
            );
        }
        if(currentChars > newChars) {
            difficulties.add(new Difficulty(
                            (int) (25 + level * 0.3),
                            (int) (5 + level * 0.2),
                            language.getTranslations(),
                            Direction.BOTH_DIRS,
                            -1,
                            Mode.ALL,
                            -1, -1,
                            currentChars > 15 ? keyboard6 : keyboard4
                    )
            );
        }
        if(newChars > 0) {
            difficulties.add(new Difficulty(
                            15,
                            4,
                            language.getTranslations(),
                            Direction.BOTH_DIRS,
                            10,
                            Mode.NEW_ONLY,
                            0.6f, 0.6f,
                            keyboard4
                    )
            );
        }
        if(currentChars > newChars) {
            difficulties.add(new Difficulty(
                            (int) (25 + level * 0.3),
                            (int) (5 + level * 0.2),
                            language.getTranslations(),
                            Direction.BOTH_DIRS,
                            10,
                            Mode.ALL,
                            0.6f, 0.6f,
                            currentChars > 15 ? keyboard6 : keyboard4
                    )
            );
        }

        difficulties.add(new Difficulty(
                        (int) (25 + level * 0.3),
                        (int) (5 + level * 0.2),
                        language.getTranslations(),
                        this.dirAll,
                        -1,
                        Mode.WORDS,
                        0.9f, 0.9f,
                        keyboardAll
                )
        );

        return difficulties;
    }

    @Override
    public int newChars(int level, Language language) {
        return 4;
    }

    @Override
    public int charCount(int level, Language language) {
        return level * 4;
    }
}
