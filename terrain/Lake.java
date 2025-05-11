package terrain;

import bonus.ResourceBonus;
import bonus.ResourceBonus.ResourceType;
import bonus.Bonus;

public class Lake extends Terrain {

  private static final int MOVEMENT_COST = 2;
  private static final int WATER_COST = 2;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.3f;
  private static final String NAME = "Lake";
  private static final int BONUS_WATER_AMOUNT = 5;

  public Lake() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public Bonus getBonus() {
    return new ResourceBonus(ResourceType.WATER, BONUS_WATER_AMOUNT, false);
  }

  @Override
  public boolean hasWaterBonus() {
    return true;
  }

  public void getStory() {

  }
}
