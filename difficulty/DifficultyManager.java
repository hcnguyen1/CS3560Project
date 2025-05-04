
public class DifficultyManager {

  private static Difficulty currentDifficulty = null;

  public static void setDifficulty(Difficulty difficulty) {
    if (currentDifficulty != null) {
      throw new IllegalStateException("Difficulty has already been set.");
    }
    currentDifficulty = difficulty;
  }

  public static Difficulty getDifficulty() {
    if (currentDifficulty == null) {
      throw new IllegalStateException("Difficulty has not been set.");
    }
    return currentDifficulty;
  }
}