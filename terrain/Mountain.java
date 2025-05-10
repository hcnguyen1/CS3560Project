package terrain;

import bonus.Bonus;
import bonus.ResourceBonus.ResourceType;
import bonus.ResourceBonus;

public class Mountain extends Terrain {

  private static final int MOVEMENT_COST = 3;
  private static final int WATER_COST = 2;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.1f;
  private static final String NAME = "Mountain";
  private static final int BONUS_GOLD_AMOUNT = 2;

  public Mountain() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public Bonus getBonus() {
    return new ResourceBonus(ResourceType.GOLD, BONUS_GOLD_AMOUNT, false);
  }

  @Override
  public boolean hasGoldBonus() {
    return true;  
  }

  public void getStory() {

  }
}
