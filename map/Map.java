package map;

import player.Player;
import terrain.Desert;
import terrain.Forest;
import terrain.Mountain;
import terrain.Plains;
import terrain.Swamp;
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

  public void generateTerrain(Difficulty difficulty) {
    //Difficulty difficulty = DifficultyManager.getDifficulty();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double roll = Math.random(); // Random value between 0 and 1
        Terrain terrain;

        double plainsRate = difficulty.getPlainsSpawnRate();
        double forestRate = plainsRate + difficulty.getForestSpawnRate();
        double mountainRate = forestRate + difficulty.getMountainSpawnRate();
        double swampRate = mountainRate + difficulty.getSwampSpawnRate();
        //double desertRate = swampRate + difficulty.getDesertSpawnRate();

        // Determine terrain based on roll
        if (roll < plainsRate) {
          terrain = new Plains();
        } else if (roll < forestRate) {
          terrain = new Forest();
        } else if (roll < mountainRate) {
          terrain = new Mountain();
        } else if (roll < swampRate) {
          terrain = new Swamp();
        } else {
          terrain = new Desert();
        }

        terrainGrid[x][y] = terrain;
      }
    }
  }

  public void spawnPlayer() {
    int startingX = 0;
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
