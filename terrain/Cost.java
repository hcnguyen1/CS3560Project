package terrain;

public class Cost {
    private int foodCost;
    private int waterCost;
    private int energyCost;

    public Cost(int e, int w, int f) {
        foodCost = f;
        waterCost = w;
        energyCost = e;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getGoldCost() {
        return energyCost;
    }

    public Cost copy() {
        Cost c = new Cost(energyCost, waterCost, foodCost);
        return c;
    }

    public Cost half() {
        Cost c = new Cost(energyCost/2, waterCost/2, foodCost/2);
        return c;
    }

}
