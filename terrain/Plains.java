package terrain;

public class Plains extends Terrain {

  private static final int MOVEMENT_COST = 1;
  private static final int WATER_COST = 1;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.5f;

  public Plains() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }
}
