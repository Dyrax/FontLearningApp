package dyrax.klingon.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface LanguageDAO {

    @Query("SELECT * FROM langprogress")
    List<LangProgress> loadAllLangProgress();

    @Query("SELECT * FROM langprogress WHERE id = :d_id")
    LangProgress loadLangProgress(long d_id);

    @Query("SELECT * FROM difficultyprogress WHERE id = :d_id")
    DifficultyProgress loadDifficultyProgress(long d_id);

    @Insert
    long insertDifficultyProgress(DifficultyProgress d);

    @Insert
    long insertLangProgress(LangProgress d);

    @Update
    void updateDifficultyProgress(DifficultyProgress d);
}
