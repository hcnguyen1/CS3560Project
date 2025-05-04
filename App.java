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

        // game will spawn player
        Map.getInstance().spawnPlayer();

        // Get the player's current location
        Player p = Map.getInstance().getPlayer();

        // get current terrain
        Terrain currentTerrain = Map.getInstance().getTerrain(p.getX(), p.getY());
        System.out.println("Player is on terrain: " + currentTerrain);
    }
}