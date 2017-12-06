package dyrax.klingon;

import dyrax.klingon.Data.DifficultyProgress;

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
}
