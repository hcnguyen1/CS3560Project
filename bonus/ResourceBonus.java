package bonus;

// import player.Player;

public class ResourceBonus implements Bonus {
    public enum ResourceType {
        FOOD, WATER, GOLD
    }

    private final ResourceType type;
    private int amount;
    private final boolean isRepeating;
    private boolean collectedThisTurn;

    public ResourceBonus(ResourceType type, int amount, boolean isRepeating) {
        this.type = type;
        this.amount = amount;
        this.isRepeating = isRepeating;
        this.collectedThisTurn = false;
    }

    // allows the Player to check potential collection
    public int getGold() {
        return type == ResourceType.GOLD && !collectedThisTurn ? amount : 0;
    }

    public int getWater() {
        return type == ResourceType.WATER && !collectedThisTurn ? amount : 0;
    }

    public int getFood() {
        return type == ResourceType.FOOD && !collectedThisTurn ? amount : 0;
    }

    public void useGold() {
        if (type == ResourceType.GOLD) {
            markCollected();
        }
    }

    public void useWater() {
        if (type == ResourceType.WATER) {
            markCollected();
        }
    }

    public void useFood() {
        if (type == ResourceType.FOOD) {
            markCollected();
        }
    }

    private void markCollected() {
        if (!isRepeating) {
            amount = 0;
        }
        collectedThisTurn = true;
    }

    public void resetTurn() {
        if (isRepeating) {
            collectedThisTurn = false;
        }
    }

    public boolean isCollectedThisTurn() {
        return collectedThisTurn;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public ResourceType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + " Bonus: " + amount + (isRepeating ? " (Repeating)" : "");
    }
}
