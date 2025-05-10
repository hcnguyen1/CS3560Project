package bonus;

import trader.ResourceBundle;

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

  public int getEnergy() {
    return type == ResourceType.ENERGY && !collectedThisTurn ? amount : 0;
  }

  public ResourceBundle useBonus(){
    if (!collectedThisTurn && amount > 0){
      markCollected();
      switch(type){
        case GOLD:
          return new ResourceBundle(0, 0, amount);
        case WATER:
          return new ResourceBundle(0, amount, 0);
        case FOOD:
          return new ResourceBundle(amount, 0, 0);
        case ENERGY:
        // doesn't do anything rn. can adjust later to implement how energy is collected / modified 
          return new ResourceBundle(0, 0, 0);  
        default:
          return new ResourceBundle(0, 0, 0);
      }
    }
    return new ResourceBundle(0, 0, 0);
  }

  public void useGold() {
    if (type == ResourceType.GOLD) {
      useBonus();
    }
  }

  public void useWater() {
    if (type == ResourceType.WATER) {
      useBonus();
    }
  }

  public void useFood() {
    if (type == ResourceType.FOOD) {
      useBonus();
    }
  }

  public void useEnergy() {
    if (type == ResourceType.ENERGY) {
      useBonus();
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
  public ResourceBundle toBundle() {
    int food = (type == ResourceType.FOOD && !collectedThisTurn) ? amount : 0;
    int water = (type == ResourceType.WATER && !collectedThisTurn) ? amount : 0;
    int gold = (type == ResourceType.GOLD && !collectedThisTurn) ? amount : 0;
    return new ResourceBundle(food, water, gold);
  }

  @Override
  public String toString() {
    return type + " Bonus: " + amount + (isRepeating ? " (Repeating)" : "");
  }
}