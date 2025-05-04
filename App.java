public class App {
    public static void main(String[] args) throws Exception {
        // Initialize the game
        System.out.println("Welcome to Wilderness Survival System (WSS)!");

        // Get map configuration (width, height, difficulty) from the helper class
        Object[] config = Helper.getMapConfiguration(); // makes a list of what config values we want
        int width = (int) config[0]; // index 0, the width
        int height = (int) config[1]; // index 1, the height
        Difficulty difficulty = (Difficulty) config[2]; // index 2, the difficulty level.

        // Generate the map
        Map map = new Map(width, height); // creates a new map of user inputted width and height.
        map.generateTerrain(difficulty);


    }
}