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

    public void setEnergyCost(int e) {
        energyCost = e;
    }

    public int getGoldCost() {
        return energyCost;
    }

    public Cost copy() {
        Cost c = new Cost(energyCost, waterCost, foodCost);
        return c;
    }

    public Cost half() {
        int e = energyCost / 2;
        int w = waterCost / 2;
        int f = foodCost / 2;

        if (energyCost % 2 == 1) {
            e += 1;
        }
        if (waterCost % 2 == 1) {
            w += 1;
        }
        if (foodCost % 2 == 1) {
            f += 1;
        }
        Cost c = new Cost(e, w, f);
        return c;
    }

    public void add(Cost c) {
        foodCost += c.getFoodCost();
        waterCost += c.getWaterCost();
        energyCost += c.getEnergyCost();
    }

    public String toString() {
        return ("Costs to get there: [Food]: " + foodCost + ", [Water]: " + waterCost + ", [Energy]: " + energyCost);
    }
}
