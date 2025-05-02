package player;
import java.util.Map;
import java.util.Random;
import terrain.Terrain;
import bonus.Bonus;
import terrain.Cost;
//import default.Difficulty;

public class Player {
    //insert private variable here
    private Vision vision;
    private Brain brain = new Brain(this);
    private int gold = 0;
    private int water = 15;//maxWater;
    private int food = 15; //maxFood;
    private int energy = 15; //maxEnergy;

    //player location
    private int locationX = 0;
    private int locationY = 3;//Map.getSize()/2;

    //constructor
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

    public void useCost(Cost c) {
        //TBC later
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

}
