package difficulty;

public interface Difficulty {
  
  double getPlainsSpawnRate();

  double getForestSpawnRate();

  double getMountainSpawnRate();

  double getLakeSpawnRate();

  double getDesertSpawnRate();

  double getTraderSpawnRate();

  int getMaxFood();

  int getMaxWater();

  int getMaxGold();

  int getMaxEnergy();
}