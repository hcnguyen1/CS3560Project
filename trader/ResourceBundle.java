package trader;

public class ResourceBundle {
    private final int food;
    private final int water;
    private final int gold;

    public ResourceBundle(int food, int water, int gold) {
        this.food = food;
        this.water = water;
        this.gold = gold;
    }

    public int getFood() { return food; }
    public int getWater() { return water; }
    public int getGold() { return gold; }

    @Override
    public String toString() {
        return String.format("%d food, %d water, %d gold", food, water, gold);
    }
    
}

