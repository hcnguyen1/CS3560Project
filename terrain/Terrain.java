package terrain;

public class Terrain {

  private int movementCost;
  private int waterCost;
  private int foodCost;
  private float spawnRate;

  private boolean hasWaterBonus;
  private boolean hasFoodBonus;
  private boolean hasGoldBonus;
  private boolean hasTraderBonus;

  // Constructor
  public Terrain(
    int movementCost,
    int waterCost,
    int foodCost,
    float spawnRate
  ) {
    this.movementCost = movementCost;
    this.waterCost = waterCost;
    this.foodCost = foodCost;
    this.spawnRate = spawnRate;
  }

  // get Cost methods
  public int getMovementCost() {
    return movementCost;
  }

  public int getWaterCost() {
    return waterCost;
  }

  public int getFoodCost() {
    return foodCost;
  }

  // Has bonuses methods
  public boolean hasWaterBonus() {
    return hasWaterBonus;
  }

  public boolean hasFoodBonus() {
    return hasFoodBonus;
  }

  public boolean hasGoldBonus() {
    return hasGoldBonus;
  }

  // get Spawn Rate methods
  public float getSpawnRate() {
    return spawnRate;
  }

  public float getTerrainSpawnRate() {
    return spawnRate;
  }

  public float getWaterSpawnRate() {
    return spawnRate;
  }

  public float getFoodSpawnRate() {
    return spawnRate;
  }

  public float getGoldSpawnRate() {
    return spawnRate;
  }

  public float getTraderSpawnRate() {
    return spawnRate;
  }
}
