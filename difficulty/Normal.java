package difficulty;

public class Normal implements Difficulty {

  @Override
  public double getDesertSpawnRate() {
    return 0.25; // 25% desert
  }

  @Override
  public double getForestSpawnRate() {
    return 0.15; // 15% Forest
  }

  @Override
  public double getMountainSpawnRate() {
    return 0.15; // 15% Mountain
  }

  @Override
  public double getPlainsSpawnRate() {
    return 0.30; // 30% Plains
  }

  @Override
  public double getLakeSpawnRate() {
    return 0.15; // 15% Lake
  }

  @Override
  public double getTraderSpawnRate() {
    return 0.10; // 10% chance to spawn trader
  }

  @Override
  public int getMaxFood() {
    return 20;
  }

  @Override
  public int getMaxWater() {
    return 20;
  }

  @Override
  public int getMaxGold() {
    return 20;
  }

  @Override
  public int getMaxEnergy() {
    return 20;
  }
}