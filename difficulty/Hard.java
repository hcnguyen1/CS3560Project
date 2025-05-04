package difficulty;

public class Hard implements Difficulty {

  @Override
  public double getDesertSpawnRate() {
    return 0.2; // 20% desert
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
    return 0.2; // 20% Plains
  }

  @Override
  public double getSwampSpawnRate() {
    return 0.2; // 20% Swamp
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