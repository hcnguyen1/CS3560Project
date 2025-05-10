package terrain;

import bonus.Bonus;
import bonus.ResourceBonus;

public class Plains extends Terrain {

  private static final int MOVEMENT_COST = 1;
  private static final int WATER_COST = 1;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.5f;
  private static final String NAME = "Plains";

  public Plains() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  /*  getTrader() and getBonus() for Player.java to use
  public Bonus getTrader() {
    return null;
  } */

  @Override
  public Bonus getBonus() {
    if (hasFoodBonus()) {
      return new ResourceBonus(ResourceBonus.ResourceType.FOOD, 1, false);
    } else if (hasWaterBonus()) {
      return new ResourceBonus(ResourceBonus.ResourceType.WATER, 1, false);
    } else if (hasGoldBonus()) {
      return new ResourceBonus(ResourceBonus.ResourceType.GOLD, 1, false);
    }
    return null;
  }

  // getNameTerrain return String
  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  public void getStory() {

  }
}
