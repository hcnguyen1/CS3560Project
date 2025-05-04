import player.Player;
import terrain.Terrain;

public class Map {

  private static Map instance;
  private int width;
  private int height;
  private Terrain[][] terrainGrid;
  private Player player; // Add a Player reference

  // private map for 1 instance
  private Map(int width, int height) {
    this.width = width;
    this.height = height;
    this.terrainGrid = new Terrain[width][height];
}

// static map
public static Map getInstance(int width, int height) {
    if (instance == null) {
        instance = new Map(width, height);
    }
    return instance;
}

// no parameter/argument Map as overload/backup
public static Map getInstance() {
  if (instance == null) {
      throw new IllegalStateException("Map is not initialized...");
  }
  return instance;
}

public Terrain getTerrain(int x, int y) {
  if (x >= 0 && x < width && y >= 0 && y < height) {
    return terrainGrid[x][y];
  }
  return null;
}

  public void generateTerrain(Difficulty difficulty) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double roll = Math.random(); // Random value between 0 and 1
        Terrain terrain;
       
        if (roll < difficulty.getPlainsSpawnRate()) {
          terrain = new Terrain(int movementCost, int waterCost, int foodCost, float spawnRate);
        } else if (
          roll <
          difficulty.getPlainsSpawnRate() +
          difficulty.getForestSpawnRate()
        ) {
          terrain = new Terrain(int movementCost, int waterCost, int foodCost, float spawnRate); 
        } else if (
          roll <
          difficulty.getPlainsSpawnRate() +
          difficulty.getForestSpawnRate() +
          difficulty.getMountainSpawnRate()
        ) {
          terrain = new Terrain(int movementCost, int waterCost, int foodCost, float spawnRate);; 
        } else if (
          roll <
          difficulty.getPlainsSpawnRate() +
          difficulty.getForestSpawnRate() +
          difficulty.getMountainSpawnRate() +
          difficulty.getSwampSpawnRate()
        ) {
          terrain = new Terrain(int movementCost, int waterCost, int foodCost, float spawnRate);; 
        } else {
          terrain = new Terrain(int movementCost, int waterCost, int foodCost, float spawnRate);;
        }

      
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
      player.setNextCoord(startingX, startingY); // Update the player's position
    }

    System.out.println(player); // Print the player's position for verification
  }

  public Player getPlayer() {
    return player;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
