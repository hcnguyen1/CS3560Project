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
  private int gold = 0;

  private int maxWater;
  private int maxFood;
  private int maxEnergy;
  private int maxGold;

  private int water;
  private int food;
  private int energy;

  // player location
  private int locationX;
  private int locationY;
  private int previousX; 
  private int previousY;

  // constructor; randomizes the brain and vision type
  public Player(int x, int y) {
    locationX = x;
    locationY = y;
    // Randomize which vision to get
    Random rng = new Random();
    int visionType = rng.nextInt(4) + 1;

    vision = switch (visionType) {
      case 2 -> new CautiousVision(this);
      case 3 -> new KeenEyedVision(this);
      case 4 -> new FarSight(this);
      default -> new FocusedVision(this);
    }; // case 1

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

    // randomize brain personality type
    int brainType = rng.nextInt(4) + 1;

    switch (brainType) {
      case 2 -> {
        // horder brain personality type; hord 2/3 of max amount
        brain.setEnergyThreshold(maxEnergy / 3 * 2);
        brain.setFoodThreshold(maxFood / 3 * 2);
        brain.setWaterThreshold(maxWater / 3 * 2);
        brain.setGoldThreshold(maxGold / 3 * 2);
      }
      case 3 -> {
        // life on the edge personality type; lowest thresholds
        brain.setEnergyThreshold(1);
        brain.setFoodThreshold(1);
        brain.setWaterThreshold(1);
        brain.setGoldThreshold(1);
      }
      case 4 -> {
        // adventurous brain type; use up 1/4 food water and energy, but don't care much
        // about hording gold
        brain.setEnergyThreshold(maxEnergy / 4);
        brain.setFoodThreshold(maxFood / 4);
        brain.setWaterThreshold(maxWater / 4);
        brain.setGoldThreshold(1);
      }
      default -> {
        // default brain type, use 1/3 of everything
        brain.setEnergyThreshold(maxEnergy / 3);
        brain.setFoodThreshold(maxFood / 3);
        brain.setWaterThreshold(maxWater / 3);
        brain.setGoldThreshold(maxGold / 3);
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
    // if there is no bonus, return
    if (b == null) {
      return;
    }
    // if it doesn't go over the max, then use the bonus
    if ((gold + b.getGold() <= maxGold)) {
      gold += b.getGold();
      b.useGold(); // depleates the gold from the bonus
    }

    if ((water + b.getWater() <= maxWater)) {
      water += b.getWater();
      b.useWater();
    }

    if ((food + b.getFood() <= maxFood)) {
      food += b.getFood();
      b.useFood();
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
    gold -= c.getGoldCost(); 
  }

  public void setNextCoord(int x, int y) {

    previousX = locationX;
    previousY = locationY;

    locationX = x;
    locationY = y;
  }

  public String getDirection(int locationX, int locationY) {
    if (locationX > previousX && locationY == previousY) {
        return "east";
    } else if (locationX < previousX && locationY == previousY) {
        return "west";
    } else if (locationX == previousX && locationY > previousY) {
        return "north";
    } else if (locationX == previousX && locationY < previousY) {
        return "south";
    } else if (locationX > previousX && locationY > previousY) {
        return "northeast";
    } else if (locationX < previousX && locationY > previousY) {
        return "northwest";
    } else if (locationX > previousX && locationY < previousY) {
        return "southeast";
    } else if (locationX < previousX && locationY < previousY) {
        return "southwest";
    } else {
        return "stationary";
    }
}

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

  public void death() {
    // player death sequence
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
    return ("\nPlayer Position is now at: (" +locationX + ", " + locationY + ")" +" \nCurrent Supplies: " + 
    "[Food]: " +food +", [Water]: " +water +", [Energy]: " +energy +", [Gold]: " +gold);
  }
}
