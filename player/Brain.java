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
        // Get the closest and second closest resource paths (food or water)
        Path closest = vision.getClosestResource();
        Path second = vision.getSecondClosestResource();
        Path trader = null;

        // If the player has enough gold, include a trader path in the options
        if (player.getGoldAmount() > goldThreshold) {
            trader = vision.getClosestTrader();
        }

        // Evaluate all options and choose the best path based on resource needs and cost
        Path bestPath = costBenefitAnalysis(closest, second, trader);

        if (bestPath != null) {
            // Set the best path as the current path and move along it
            currentPath = bestPath;
            currentPath.takePath();

            // If the new tile has a resource bonus, collect it
            if (player.getCurrentTerrain().hasFoodBonus() || player.getCurrentTerrain().hasGoldBonus() || player.getCurrentTerrain().hasWaterBonus()) {
                player.useBonus();
            }

            // Apply the movement cost of the tile
            player.useCost(bestPath.getCost());
        }
        else {
            // If no valid paths were found, stay in place and recover half the cost
            this.stay();
        }

    }

    private void equalizerStrategy() {
        Path first = null;
        Path second = null;
    
        // If food is below the threshold, look for the two closest food sources
        if (player.getFoodAmount() < foodThreshold) {
            first = vision.getClosestFood();
            second = vision.getSecondClosestFood();
        }
    
        // If water is below the threshold, look for the two closest water sources
        if (player.getWaterAmount() < waterThreshold) {
            first = vision.getClosestWater();
            second = vision.getSecondClosestWater();
        }
    
        // Always consider a path to the closest trader
        Path trader = vision.getClosestTrader();
    
        // Choose the best path out of the two resources and the trader
        Path bestPath = costBenefitAnalysis(first, second, trader);

        if (bestPath != null) {
            // Move along the selected best path
            currentPath = bestPath;
            currentPath.takePath();
    
            // Use bonus on the tile if one exists
            if (player.getCurrentTerrain().hasBonus()) {
                player.useBonus();
            }
    
            // Apply movement cost
            player.useCost(bestPath.getCost());
        } else {
            // If no path is found, stay on current tile
            this.stay(); // fallback
        }
    }

    private void lowOnOneStrategy() {
        Path p1 = null;
        Path p2 = null;


        // If food is below the threshold, get the two closest food paths
        if (player.getFoodAmount() < foodThreshold) {
            p1 = vision.getClosestFood();
            p2 = vision.getSecondClosestFood();
        }
    
        // If water is below the threshold, get the two closest water paths
        if (player.getWaterAmount() < waterThreshold) {
            p1 = vision.getClosestWater();
            p2 = vision.getSecondClosestWater();
        }
    
        // Choose the best path between the two resource paths (no trader considered here)
        Path bestPath = costBenefitAnalysis(p1, p2, null); // null trader path
    
        if (bestPath != null) {
            // Move along the best path
            currentPath = bestPath;
            currentPath.takePath();
    
            // Use any bonus on the new tile
            if (player.getCurrentTerrain().hasBonus()) {
                player.useBonus();
            }
    
            // Apply cost for moving to the new tile
            player.useCost(bestPath.getCost());
        } else {
            // Stay in place if no useful path is found
            this.stay(); // fallback if no valid path
        }
    
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

    private Path costBenefitAnalysis (Path p1, Path p2, Path traderPat) {
        Path best = null;



    }
}
