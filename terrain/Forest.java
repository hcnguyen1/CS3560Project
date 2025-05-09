package terrain;

public class Forest extends Terrain {

  private static final int MOVEMENT_COST = 2;
  private static final int WATER_COST = 1;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.3f;
  private static final String NAME = "Forest";

  public Forest() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public boolean hasFoodBonus() {
    return true;
  }

  public void getStory() {

  }
}
