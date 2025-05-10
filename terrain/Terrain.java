package terrain;

import bonus.Bonus;

public class Terrain {

  private Cost costs;
  private float spawnRate;
  private trader.Trader trader;

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

  // This is currently being used with subclasses of Terrain
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
    return trader != null;
  }

  public trader.Trader getTrader() {
    return trader;
  }

  public void setTrader(trader.Trader t) {
    this.trader = t;
  }

  // getTrader() and getBonus() for Player.java to use
  public Bonus getBonus() {
    return null;
  }

  public String getNameTerrain() {
    return "Terrain";
  }
}
