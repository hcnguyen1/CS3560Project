package difficulty;

public interface Difficulty {
  
  double getPlainsSpawnRate();

  double getForestSpawnRate();

  double getMountainSpawnRate();

  double getLakeSpawnRate();

  double getDesertSpawnRate();

  int getMaxFood();

  int getMaxWater();

  int getMaxGold();

  int getMaxEnergy();
}