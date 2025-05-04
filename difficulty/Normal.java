package difficulty;

public class Normal implements Difficulty {

  @Override
  public double getDesertSpawnRate() {
    return 0.15; // 15% desert
  }

  @Override
  public double getForestSpawnRate() {
    return 0.2; // 20% Forest
  }

  @Override
  public double getMountainSpawnRate() {
    return 0.2; // 20% Mountain
  }

  @Override
  public double getPlainsSpawnRate() {
    return 0.3; // 30% Plains
  }

  @Override
  public double getSwampSpawnRate() {
    return 0.15; // 15% Swamp
  }

  @Override
  public int getMaxFood() {
    return 75;
  }

  @Override
  public int getMaxWater() {
    return 75;
  }

  @Override
  public int getMaxGold() {
    return 75;
  }

  @Override
  public int getMaxEnergy() {
    return 75;
  }
}