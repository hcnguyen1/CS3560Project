package player;
import java.util.Map;
import java.util.Random;
import terrain.Terrain;
import bonus.Bonus;
import terrain.Cost;
//import default.Difficulty; the difficulty interface will have the values for each difficulty

public class Player {
    //insert private variable here
    private Vision vision;
    private Brain brain = new Brain(this);
    private int gold = 0;
    private int water = maxWater;
    private int food = maxFood;
    private int energy = maxEnergy;

    //player location
    private int locationX = 0;
    private int locationY = 3;//Map.getSize()/2;

    //constructor; randomizes the brain and vision type
    public Player() {
        //Randomize which vision to get
        Random rng = new Random();
        int visionType = rng.nextInt(4) + 1;
        
        vision = switch (visionType) {
            case 2 -> new CautiousVision();
            case 3 -> new KeenEyedVision();
            case 4 -> new FarSight();
            default -> new FocusedVision();
        }; //case 1

        brain.setVision(vision);

        //randomize brain personality type
        int brainType = rng.nextInt(4) + 1;

        switch (brainType) {
            case 2 -> {
                //horder brain personality type; hord 2/3 of max amount
                brain.setEnergyThreshold(maxEnergy/3*2);
                brain.setFoodThreshold(maxFood/3*2);
                brain.setWaterThreshold(maxWater/3*2);
                brain.setGoldThreshold(maxGold/3*2);
            }
            case 3 -> {
                //life on the edge personality type; lowest thresholds
                brain.setEnergyThreshold(1);
                brain.setFoodThreshold(1);
                brain.setWaterThreshold(1);
                brain.setGoldThreshold(1);
            }
            case 4 -> {
                //adventurous brain type; use up 1/4 food water and energy, but don't care much about hording gold
                brain.setEnergyThreshold(maxEnergy/4);
                brain.setFoodThreshold(maxFood/4);
                brain.setWaterThreshold(maxWater/4);
                brain.setGoldThreshold(1);
            }
            default -> {
                //default brain type, use 1/3 of everything
                brain.setEnergyThreshold(maxEnergy/3);
                brain.setFoodThreshold(maxFood/3);
                brain.setWaterThreshold(maxWater/3);
                brain.setGoldThreshold(maxGold/3);
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
        Map.getTerrain(locationX, locationY);
    }

    public int getX() {
        return locationX;
    }

    public int getY() {
        return locationY;
    }

    //Uses the bonuses if it does not exceed the maximum
    //if there is no bonus, bonus = 0 added will have no effect
    public void useBonus() {
        Bonus b = this.getCurrentTerrain().getBonus();
        //if it doesn't go over the max, then use the bonus
        if ((gold + b.getGold() <= maxGold)) {
            gold += b.getGold();
            b.useGold(); //depleates the gold from the bonus
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
        Bonus b = this.getCurrentTerrain().getTrader();
        //TBC
    }

    //assume brain already checked the costs and it can afford it
    public void useCost(Cost c) {
        water -= c.getWaterCost();
        food -= c.getFoodCost();
        energy -= c.getEnergyCost();
        gold -= c.getGoldCost();
    }

    public void setNextCoord(int x, int y) {
        locationX = x;
        locationY = y;
    }

    public int getGoldAmount(){
        return gold;
    }
    public int getWaterAmount(){
        return water;
    }
    public int getFoodAmount(){
        return food;
    }
    public int getEnergyAmount(){
        return energy;
    }

    public void death() {
        //player death sequence
    }

    public boolean canAfford(Cost cost) {
        return this.getFoodAmount() >= cost.getFoodCost() &&
               this.getWaterAmount() >= cost.getWaterCost() &&
               this.getEnergyAmount() >= cost.getEnergyCost();
    }    

}
