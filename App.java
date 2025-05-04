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

        DifficultyManager.setDifficulty((Difficulty) config[2]); // sets difficulty globally.

        // Generate the map using all values
        Map.getInstance(width, height).generateTerrain(DifficultyManager.getDifficulty());

        // game will spawn player
        Map.getInstance().spawnPlayer();

        // Get the player's current location
        Player p = Map.getInstance().getPlayer();

        // get current terrain
        Terrain currentTerrain = Map.getInstance().getTerrain(p.getX(), p.getY());
        System.out.println("Player is on terrain: " + currentTerrain);
    }
}