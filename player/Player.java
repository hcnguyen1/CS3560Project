package player;

import bonus.Bonus;
import difficulty.*;
import java.util.Random;
import map.Map;
import terrain.Cost;
import terrain.Terrain;

//import default.Difficulty; the difficulty interface will have the values for each difficulty

public class Player {

  // insert private variable here
  private Vision vision;
  private Brain brain = new Brain(this);
  public int gold = 0;

  public int maxWater;
  public int maxFood;
  public int maxEnergy;
  public int maxGold;

  public int water;
  public int food;
  public int energy;

  // player location
  private int locationX;
  private int locationY;
  private int previousX;
  private int previousY;

  // constructor; randomizes the brain and vision type
  public Player(int x, int y) {
    locationX = x;
    locationY = y;

    System.out.print("\n\nPlayer has been created.\n\nPlayer Vision Type: ");

    // Randomize which vision to get
    Random rng = new Random();
    int visionType = rng.nextInt(4) + 1;

    if (visionType == 2) {
      vision = new CautiousVision(this);
      System.out.println("Cautious");
    } else if (visionType == 3) {
      vision = new KeenEyedVision(this);
      System.out.println("Keen Eyed");
    } else if (visionType == 4) {
      vision = new FarSight(this);
      System.out.println("Far Sighted");
    } else {
      vision = new FocusedVision(this);
      System.out.println("Focused Vision");
    }

    brain.setVision(vision);

    // Max values based on dificulty
    Difficulty difficulty = DifficultyManager.getDifficulty();
    maxFood = difficulty.getMaxFood();
    maxWater = difficulty.getMaxWater();
    maxGold = difficulty.getMaxGold();
    maxEnergy = difficulty.getMaxEnergy();

    // Initialize resource amounts
    food = maxFood;
    water = maxWater;
    energy = maxEnergy;
    gold = maxGold;

    // randomize brain personality type
    int brainType = rng.nextInt(4) + 1;

    System.out.print("Player Personality Type: ");

    switch (brainType) {
      case 2 -> {
        // horder brain personality type; hord 2/3 of max amount
        brain.setEnergyThreshold(maxEnergy / 3 * 2);
        brain.setFoodThreshold(maxFood / 3 * 2);
        brain.setWaterThreshold(maxWater / 3 * 2);
        brain.setGoldThreshold(maxGold / 3 * 2);
        System.out.println("Horder");
      }
      case 3 -> {
        // life on the edge personality type; lowest thresholds
        brain.setEnergyThreshold(1);
        brain.setFoodThreshold(1);
        brain.setWaterThreshold(1);
        brain.setGoldThreshold(1);
        System.out.println("Risk Taker");
      }
      case 4 -> {
        // adventurous brain type; use up 1/4 food water and energy, but don't care much
        // about hording gold
        brain.setEnergyThreshold(maxEnergy / 4);
        brain.setFoodThreshold(maxFood / 4);
        brain.setWaterThreshold(maxWater / 4);
        brain.setGoldThreshold(1);
        System.out.println("Adventurer");
      }
      default -> {
        // default brain type, use 1/3 of everything
        brain.setEnergyThreshold(maxEnergy / 3);
        brain.setFoodThreshold(maxFood / 3);
        brain.setWaterThreshold(maxWater / 3);
        brain.setGoldThreshold(maxGold / 3);
        System.out.println("Default");
      }
    }
  }

  public void makeMove() {
    brain.makeMove();
  }

  public Vision getVision() {
    return vision;
  }

  public Terrain getCurrentTerrain() {
    return Map.getInstance().getTerrain(locationX, locationY);
  }

  public int getX() {
    return locationX;
  }

  public int getY() {
    return locationY;
  }

  // Uses the bonuses if it does not exceed the maximum
  // if there is no bonus, bonus = 0 added will have no effect
  public void useBonus() {
    Bonus b = this.getCurrentTerrain().getBonus();

    if (b != null) {
      b.useBonus(this);
    } else {
      return;
    }
  }

  public void useGold(int goldAmount) {
    int newGold = gold + goldAmount;
    if (newGold > maxGold) {
      gold = maxGold;
    } else {
      gold = newGold;
    }
  }

  public void useWater(int waterAmount) {
    int newWater = water + waterAmount;
    if (newWater > maxWater) {
      water = maxWater;
    } else {
      water = newWater;
    }
  }

  public void useFood(int foodAmount) {
    int newFood = food + foodAmount;
    if (newFood > maxFood) {
      food = newFood;
    } else {
      food = newFood;
    }
  }

  public void useTrader() {
    // Bonus b = this.getCurrentTerrain().getTrader();
    // TBC
  }

  // assume brain already checked the costs and it can afford it
  public void useCost(Cost c) {
    water -= c.getWaterCost();
    food -= c.getFoodCost();
    energy -= c.getEnergyCost();
    // gold -= c.getGoldCost();
  }

  public void setNextCoord(int x, int y) {

    previousX = locationX;
    previousY = locationY;

    locationX = x;
    locationY = y;
  }

  public String getDirection(int locationX, int locationY) {
    if (locationX > previousX && locationY == previousY) {
      return "moving east.";
    } else if (locationX < previousX && locationY == previousY) {
      return "moving west.";
    } else if (locationX == previousX && locationY > previousY) {
      return "moving north.";
    } else if (locationX == previousX && locationY < previousY) {
      return "moving south.";
    } else if (locationX > previousX && locationY > previousY) {
      return "moving northeast.";
    } else if (locationX < previousX && locationY > previousY) {
      return "moving northwest.";
    } else if (locationX > previousX && locationY < previousY) {
      return "moving southeast.";
    } else if (locationX < previousX && locationY < previousY) {
      return "moving southwest.";
    } else {
      return "stationary.";
    }
  }

  // get resource amounts
  public int getGoldAmount() {
    return gold;
  }

  public int getWaterAmount() {
    return water;
  }

  public int getFoodAmount() {
    return food;
  }

  public int getEnergyAmount() {
    return energy;
  }

  public boolean canAfford(Cost cost) {
    return (this.getFoodAmount() >= cost.getFoodCost() &&
        this.getWaterAmount() >= cost.getWaterCost() &&
        this.getEnergyAmount() >= cost.getEnergyCost());
  }

  public boolean isDead() {
    return food <= 0 || water <= 0 || energy <= 0;
  }

  @Override
  public String toString() {
    return ("\nPlayer Position is now at: (" + locationX + ", " + locationY + ")" + " \nCurrent Supplies: " +
        "[Food]: " + food + ", [Water]: " + water + ", [Energy]: " + energy + ", [Gold]: " + gold);
  }

}
