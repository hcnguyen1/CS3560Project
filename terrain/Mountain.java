package terrain;

public class Mountain extends Terrain {

  private static final int MOVEMENT_COST = 3;
  private static final int WATER_COST = 2;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.1f;

  public Mountain() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }
}
