package terrain;

import bonus.Bonus;

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

  // Placeholder
  public boolean hasFoodBonus() {
    return false;
  }

  public boolean hasWaterBonus() {
    return false;
  }

  public boolean hasGoldBonus() {
    return false;
  }

  public boolean hasTrader() {
    return false;
  }

  // getTrader() and getBonus() for Player.java to use
  // public Bonus getTrader() {
  //   return null;
  // }

  public Bonus getBonus() {
    return null;
  }
}
