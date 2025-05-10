package difficulty;

public class Hard implements Difficulty {

  @Override
  public double getDesertSpawnRate() {
    return 0.35; // 35% desert
  }

  @Override
  public double getForestSpawnRate() {
    return 0.10; // 20% Forest
  }

  @Override
  public double getMountainSpawnRate() {
    return 0.10; // 20% Mountain
  }

  @Override
  public double getPlainsSpawnRate() {
    return 0.35; // 35% Plains
  }

  @Override
  public double getLakeSpawnRate() {
    return 0.10; // 20% Lake
  }

  @Override
  public double getTraderSpawnRate() {
    return 0.05; // 5% chance to spawn trader
  }

  @Override
  public int getMaxFood() {
    return 50;
  }

  @Override
  public int getMaxWater() {
    return 50;
  }

  @Override
  public int getMaxGold() {
    return 50;
  }

  @Override
  public int getMaxEnergy() {
    return 50;
  }
}