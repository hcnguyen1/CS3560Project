public class Easy implements Difficulty {


    @Override
    public double getDesertSpawnRate() {
        return 0.05; // 5% desert
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
        return 0.4; // 40% Plains
    }

    @Override
    public double getSwampSpawnRate() {
        return 0.05; // 5% Swamp
    }


}