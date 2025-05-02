package player;
import terrain.Path;
import terrain.Cost;

public class Brain {

    int waterThreshold = 5;
    int foodThreshold = 5;
    int goldThreshold = 1;
    int energyThreshold = 5;
    Path currentPath;
    double n = 1.5; //multiplier 
    Player player;
    Vision vision;

    public Brain (Player p) {
        player = p;
    }

    public void setVision(Vision v) {
        vision = v;
    }

    public void makeMove() {
        int water = player.getWaterAmount();
        int food = player.getFoodAmount();
        int energy = player.getEnergyAmount();
        int gold = player.getGoldAmount();

        //determine which strategy to use
        if (food >= foodThreshold && water >= waterThreshold) { //both food and water is above threshold
            if (energy < energyThreshold) {
                this.energyConversion();
            } else {
                this.richStrategy();
            }
        } else if (food <= foodThreshold && water <= waterThreshold) { //both food and water is below threshold
            this.scavengerStrategy();
        } else { //one is below threshold, one is above

            //there is n times more water than food or food than water or player has gold to spend
            if (food >= n*water || water >= n*food || gold > goldThreshold) {
                this.equalizerStrategy();
            } else { //the player has about an equal amount of food and water but one is below the threshold
                this.lowOnOneStrategy();
            }
        }

    }

    //use half the costs of the terrain where the player is staying
    private void stay() {
        Cost c = player.getCurrentTerrain().getCost();
        Cost.half(c);
        player.useCost(c);
    }

    private void energyConversion() {
        this.stay();
    }

    private void scavengerStrategy() {
        
    }

    private void equalizerStrategy() {
        
    }

    private void lowOnOneStrategy() {
        
    }

    //Take the easiest path
    //generates a new easiest path when currentPath is null, otherwise continue on the path
    private void richStrategy() {
        if (currentPath == null || currentPath.getNumSteps == 0) {
            Path paths[] = vision.easiestPath();
            currentPath = paths[0];
            currentPath.takePath();
        } else { 
            currentPath.takePath();
        }

        //use the bonuses at the terrain the player enters
        //the path object takes care of the costs
        player.useBonus();
    }
}
