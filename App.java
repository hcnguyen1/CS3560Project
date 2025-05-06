import difficulty.Difficulty;
import difficulty.DifficultyManager;
import map.Map;
import player.Player;
import terrain.Terrain;

public class App {

  public static void main(String[] args) throws Exception {
    // Initialize the game
    System.out.println("Welcome to Wilderness Survival System (WSS)!");

    // Get map configuration (width, height, difficulty) from the helper class
    Object[] config = Helper.getMapConfiguration(); // makes a list of what config values we want
    int width = (int) config[0]; // index 0, the width
    int height = (int) config[1]; // index 1, the height
    Difficulty difficulty = (Difficulty) config[2]; // difficulty option

    // Set the difficulty globally
    DifficultyManager.setDifficulty(difficulty);

    // Generate the map using the width and height. The difficulty is already set globally.
    Map.getInstance(width, height).generateTerrain(difficulty);

    // Spawn the player
    Map.getInstance().spawnPlayer();

    // Get the player instance
    Player player = Map.getInstance().getPlayer();

    while (true) {
      
      // Check if the player has reached the rightmost column
      if (player.getX() == width - 1) {
        System.out.println("Congratulations! You've won!");
        break;
      }

      // check if player is dead, if they aren't then they are able to move.
      if (player.isDead()) { // still undefined
        System.out.println("Game Over! The player has died.");
        System.exit(1); // program closes
      } else {
        // Make the player move if they are not dead
        // Display the player's current state
        
        Terrain t = Map.getInstance().getTerrain(player.getX(), player.getY());
        System.out.println("Current Terrain: " + t.getNameTerrain());
        player.makeMove();
        System.out.println("\nPlayer is moving " + player.getDirection(player.getX(), player.getY()));
        Thread.sleep(1000);
        System.out.println(player);
      }
    }
  }
}
