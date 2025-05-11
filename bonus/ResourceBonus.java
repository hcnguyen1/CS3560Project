package bonus;

// import trader.ResourceBundle;
import player.Player;

public class ResourceBonus implements Bonus {

  public static enum ResourceType {
    FOOD,
    WATER,
    GOLD,
    ENERGY,
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

  public int getGold() {
    return type == ResourceType.GOLD && !collectedThisTurn ? amount : 0;
  }

  public int getWater() {
    return type == ResourceType.WATER && !collectedThisTurn ? amount : 0;
  }

  public int getFood() {
    return type == ResourceType.FOOD && !collectedThisTurn ? amount : 0;
  }

  public void useBonus(Player player) {
    if (!collectedThisTurn && amount > 0) {
      int gain = 0;
      markCollected();
      switch (type) {
        case GOLD:
          player.useGold(this.getGold());
          System.out.println("Gold acquired!");
          break;
        case WATER:
          player.useWater(this.getWater());
          System.out.println("Water acquired!");
          break;
        case FOOD:
          player.useFood(this.getFood());
          System.out.println("Food acquired!");
          break;
        default:
          break;
      }
    }
  }

  public void useGold() {
    if (type == ResourceType.GOLD) {
      amount = 0;
    }
  }

  public void useWater() {
    if (type == ResourceType.WATER) {
      amount = 0;
    }
  }

  public void useFood() {
    if (type == ResourceType.FOOD) {
      amount = 0;
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

  // Converts this bonus to a ResourceBundle with the appropriate field set
  // public ResourceBundle toBundle() {
  // int food = (type == ResourceType.FOOD && !collectedThisTurn) ? amount : 0;
  // int water = (type == ResourceType.WATER && !collectedThisTurn) ? amount : 0;
  // int gold = (type == ResourceType.GOLD && !collectedThisTurn) ? amount : 0;
  // return new ResourceBundle(food, water, gold);
  // }

  @Override
  public String toString() {
    return type + " Bonus: " + amount + (isRepeating ? " (Repeating)" : "");
  }
}