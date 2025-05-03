public class Hard implements Difficulty {



    @Override
    public double getDesertSpawnRate() {
        return 0.1; // 20% Swamp
    }

    @Override
    public double getForestSpawnRate() {
        return 0.3; // 20% Forest
    }

    @Override
    public double getMountainSpawnRate() {
        return 0.1; // 20% Mountain
    }

    @Override
    public double getPlainsSpawnRate() {
        return 0.5; // 20% Plains
    }

    @Override
    public double getSwampSpawnRate() {
        return 0.1; // 20% Swamp
    }
}