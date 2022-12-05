package factories;

import models.DifficultyLevel;

import java.util.Locale;

public class DifficultyLevelFactory {
    public static DifficultyLevel getDifficultyLevelByName(String difficultyLevelName) {
       return DifficultyLevel.valueOf(difficultyLevelName.toUpperCase());
    }
}
