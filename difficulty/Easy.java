package difficulty;

public class Easy implements Difficulty {

  @Override
  public double getDesertSpawnRate() {
    return 0.125; // 12.5% desert
  }

  @Override
  public double getForestSpawnRate() {
    return 0.25; // 25% Forest
  }

  @Override
  public double getMountainSpawnRate() {
    return 0.25; // 25% Mountain
  }

  @Override
  public double getPlainsSpawnRate() {
    return 0.125; // 12.5% Plains
  }

  @Override
  public double getLakeSpawnRate() {
    return 0.25; // 20% lake
  }

  @Override
  public double getTraderSpawnRate() {
    return 0.15; // 15% chance to spawn trader
  }

  @Override
  public int getMaxFood() {
    return 100;
  }

  @Override
  public int getMaxWater() {
    return 100;
  }

  @Override
  public int getMaxGold() {
    return 100;
  }

  @Override
  public int getMaxEnergy() {
    return 100;
  }
}