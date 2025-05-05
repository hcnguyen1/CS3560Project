import map.Map;
import player.Player;
import terrain.Terrain;
import difficulty.Difficulty;
import difficulty.DifficultyManager;


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

        // Game loop
        while (true) {
            // Display the player's current state
            System.out.println("Player Position: (" + player.getX() + ", " + player.getY() + ")");
            System.out.println("Food: " + player.getFoodAmount() + ", Water: " + player.getWaterAmount() +
                               ", Energy: " + player.getEnergyAmount() + ", Gold: " + player.getGoldAmount());
            Terrain currentTerrain = Map.getInstance().getTerrain(player.getX(), player.getY());
            System.out.println("Current Terrain: " + currentTerrain);

            // Check if the player has reached the rightmost column
            if (player.getX() == width - 1) {
                System.out.println("Congratulations! You reached the goal!");
                break;
            }

            // Check if player is dead
            if (player.getFoodAmount() <= 0 || player.getWaterAmount() <= 0 || player.getEnergyAmount() <= 0) {
                System.out.println("Game Over! The player has died.");
                break;
            }

            // Make the player move
            player.makeMove();
        }
    }
}