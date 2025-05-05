package bonus;

public interface Bonus {
  // Getters for the bonus values
  public int getGold();

  public int getWater();

  public int getFood();

  public int getEnergy();

  // Player calls these method to deplete the bonus from the terrain
  public void useGold();

  public void useWater();

  public void useFood();

  public void useEnergy();
}
