package dyrax.klingon;

import dyrax.klingon.Data.DifficultyProgress;
import dyrax.klingon.Data.LangProgress;

public class Global {
    public static class GlobalException extends RuntimeException {
        public GlobalException(String message) {
            super(message);
        }
    }

    private static DifficultyProgress difficultyProgress;

    public static DifficultyProgress getDifficultyProgress() {
        if(difficultyProgress == null)
            throw new GlobalException("difficultyProgress was not defined");
        DifficultyProgress diff = difficultyProgress;
        difficultyProgress = null;
        return diff;
    }

    public static void setDifficultyProgress(DifficultyProgress difficultyProgress) {
        if(Global.difficultyProgress != null)
            throw new GlobalException("difficultyProgress is already defined");
        Global.difficultyProgress = difficultyProgress;
    }

    private static LangProgress langProgress;

    public static LangProgress getLangProgress() {
        if(langProgress == null)
            throw new GlobalException("langProgress was not defined");
        LangProgress diff = langProgress;
        langProgress = null;
        return diff;
    }

    public static void setLangProgress(LangProgress langProgress) {
        if(Global.langProgress != null)
            throw new GlobalException("langProgress is already defined");
        Global.langProgress = langProgress;
    }
}
