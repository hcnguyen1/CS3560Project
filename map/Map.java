package map;

import player.Player;
import terrain.Desert;
import terrain.Forest;
import terrain.Mountain;
import terrain.Plains;
import terrain.Lake;
import terrain.Terrain;
import difficulty.Difficulty;


public class Map {

  private static Map instance;
  private int width;
  private int height;
  private Terrain[][] terrainGrid;
  private Player player; // Add a Player reference

  Map(int width, int height) {
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

  // Return string of the terrain at the given coordinates
  public String getTerrainString(int x, int y) {
    Terrain terrain = getTerrain(x, y);
    if (terrain != null) {
      return terrain.toString(); // Call the toString() method of the Terrain object
    }
    return "Invalid coordinates";
  }

  // generates terrain using the map as a grid. The rolls are the 
  // chances out of 100 indepdent from other terrain spawn rates.
  // spawnTrader is called here as when the terrain is generated, the trader is also generated.
  public void generateTerrain(Difficulty difficulty) {
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            int roll = (int)(Math.random() * 100);
            Terrain terrain;

            int plainsRate = (int)(difficulty.getPlainsSpawnRate() * 100);
            int forestRate = (int)(difficulty.getForestSpawnRate() * 100);
            int mountainRate = (int)(difficulty.getMountainSpawnRate() * 100);
            int lakeRate = (int)(difficulty.getLakeSpawnRate() * 100);

            if (roll < plainsRate) {
                terrain = new Plains();
            } else if (roll < plainsRate + forestRate) {
                terrain = new Forest();
            } else if (roll < plainsRate + forestRate + mountainRate) {
                terrain = new Mountain();
            } else if (roll < plainsRate + forestRate + mountainRate + lakeRate) {
                terrain = new Lake();
            } else {
                terrain = new Desert();
            }

            terrainGrid[x][y] = terrain;

            // spawns a trader using the current terrain and difficulty setting
            spawnTrader(terrain, difficulty);
        }
    }
  }

  private void spawnTrader(Terrain terrain, Difficulty difficulty) {
    if (Math.random() < difficulty.getTraderSpawnRate()) { // can be 15% 10% or 5%
        int type = (int)(Math.random() * 3);
        switch (type) {
            case 0: terrain.setTrader(new trader.FoodTrader()); break;
            case 1: terrain.setTrader(new trader.WaterTrader()); break;
            case 2: terrain.setTrader(new trader.GoldTrader()); break;
        }
    }
  }

  public void spawnPlayer() {
    int startingX = 1;
    int startingY = height / 2; // Calculate the middle row

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
