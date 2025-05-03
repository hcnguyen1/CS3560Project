package terrain;

public class Terrain {

  private Cost costs;
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
    costs = new Cost(movementCost, waterCost, foodCost);
    this.spawnRate = spawnRate;
  }

  // get Costs
  public Cost getCost() {
    return costs.copy();
  }

  // Has bonuses methods
  //if we change how bonuses store data to be more similar to Costs, we can do return bonus.getWaterCost == 0; etc
  //and we don't need the variables either 
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
