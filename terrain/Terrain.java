package terrain;

public class Terrain {

  private Cost costs;
  private float spawnRate;

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
