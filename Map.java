public class Map {
    private int width;
    private int height;
    private Terrain[][] terrainGrid;
    private Player player; // Add a Player reference

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.terrainGrid = new Terrain[width][height];
    }

    public void generateTerrain(Difficulty difficulty) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double roll = Math.random(); // Random value between 0 and 1
                Terrain terrain;

                // Determine terrain type based on difficulty spawn rates
                if (roll < difficulty.getPlainsSpawnRate()) {
                    terrain = new Terrain("Plains", x, y); // Spawn Plains
                } else if (roll < difficulty.getPlainsSpawnRate() + difficulty.getForestSpawnRate()) {
                    terrain = new Terrain("Forest", x, y); // Spawn Forest
                } else if (roll < difficulty.getPlainsSpawnRate() + difficulty.getForestSpawnRate() + difficulty.getMountainSpawnRate()) {
                    terrain = new Terrain("Mountain", x, y); // Spawn Mountain
                } else if (roll < difficulty.getPlainsSpawnRate() + difficulty.getForestSpawnRate() + difficulty.getMountainSpawnRate() + difficulty.getSwampSpawnRate()) {
                    terrain = new Terrain("Swamp", x, y); // Spawn Swamp
                } else {
                    terrain = new Terrain("Desert", x, y); // Spawn Desert
                }

                // Assign the terrain to the grid
                terrainGrid[x][y] = terrain;
            }
        }
    }

    public void spawnPlayer() {
        int startingX = height / 2; // Calculate the middle row
        int startingY = 0; // The west-most column is always 0

        // Create a new player or update the existing player's position
        if (player == null) {
            player = new Player(startingX, startingY); // Create a new player
        } else {
            player.setPosition(startingX, startingY); // Update the player's position
        }

        System.out.println(player); // Print the player's position for verification
    }

    public Player getPlayer() {
        return player;
    }

    public Terrain getTerrain(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return terrainGrid[x][y];
        }
        return null; 
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
