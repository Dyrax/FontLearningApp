package dyrax.klingon.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {LangProgress.class, DifficultyProgress.class}, version = 1)
@TypeConverters({RoomConverter.class})
public abstract class LanguageDatabase extends RoomDatabase {
    public abstract LanguageDAO languageDAO();

    private static LanguageDatabase instance;
    public static void setInstance(LanguageDatabase value) { instance = value; }
    public static LanguageDatabase getInstance() { return instance;}
}
