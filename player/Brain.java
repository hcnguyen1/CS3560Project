package player;

import terrain.Path;
import terrain.Cost;
import terrain.Terrain;
import java.util.List;

public class Brain {

    int waterThreshold;
    int foodThreshold;
    int goldThreshold;
    int energyThreshold;
    Path currentPath;
    double n = 1.2; // multiplier
    Player player;
    Vision vision;

    public Brain(Player p) {
        player = p;
    }

    public void setVision(Vision v) {
        vision = v;
    }

    // These values can determine brain personality
    public void setWaterThreshold(int w) {
        waterThreshold = w;
    }

    public void setFoodThreshold(int f) {
        foodThreshold = f;
    }

    public void setGoldThreshold(int g) {
        goldThreshold = g;
    }

    public void setEnergyThreshold(int e) {
        energyThreshold = e;
    }

    public void makeMove() {
        int water = player.getWaterAmount();
        int food = player.getFoodAmount();
        int energy = player.getEnergyAmount();
        int gold = player.getGoldAmount();

        // determine which strategy to use
        if (food >= foodThreshold && water >= waterThreshold) { // both food and water is above threshold
            if (energy < energyThreshold) {
                this.energyConversion();
            } else {
                this.richStrategy();
            }
        } else if (food <= foodThreshold && water <= waterThreshold) { // both food and water is below threshold
            this.scavengerStrategy();
        } else { // one is below threshold, one is above

            // there is n times more water than food or food than water or player has gold
            // to spend
            if (food >= n * water || water >= n * food || gold > goldThreshold) {
                this.equalizerStrategy();
            } else { // the player has about an equal amount of food and water but one is below the
                     // threshold
                this.lowOnOneStrategy();
            }
        }

    }

    // use half the costs of the terrain where the player is staying and gain energy
    // if the player is staying, it has either run out of energy or out of resources
    // and is stuck
    private void stay() {
        Cost c = player.getCurrentTerrain().getCost().half();

        // gain 2 energy for staying
        c.setEnergyCost(-2);

        player.useCost(c);

        player.setNextCoord(player.getX(), player.getY());
    }

    private void energyConversion() {
        System.out.println("Not enough energy. Using energy conservation strategy.");
        this.stay();
    }

    private void scavengerStrategy() {
        System.out.println("Critical. Low on food and water. Using scavenger strategy.");
        // Get the closest and second closest resource paths (food or water)
        Path closest;
        Path second;
        int foodAmount = player.getFoodAmount();
        int waterAmount = player.getWaterAmount();

        // if lower on food, look for food; if lower on water, look for water
        // if low on both, look for both
        if (foodAmount < waterAmount) {
            closest = vision.closestFood();
            second = vision.secondClosestFood();
        } else if (waterAmount > foodAmount) {
            closest = vision.closestWater();
            second = vision.secondClosestWater();
        } else {
            closest = vision.closestFood();
            second = vision.closestWater();
        }
        Path trader = null;

        // If the player has any gold, find a trader
        if (player.getGoldAmount() > 0) {
            trader = vision.closestTrader();
        }

        // Evaluate all options and choose the best path based on resource needs and
        // cost
        Path bestPath = costBenefitAnalysis(closest, second, trader);

        if (bestPath != null) {
            // Set the best path as the current path and move along it
            currentPath = bestPath;
            currentPath.takePath();

            // If the new tile has a resource bonus, collect it
            player.useBonus();
        } else {
            // check second option; if food lower than water, check water
            // if water lower than food, check food
            // if low on both, check second closest path for both
            if (foodAmount < waterAmount) {
                closest = vision.closestWater();
                second = vision.secondClosestWater();
            } else if (waterAmount > foodAmount) {
                closest = vision.closestFood();
                second = vision.secondClosestFood();
            } else {
                closest = vision.secondClosestFood();
                second = vision.secondClosestWater();
            }

            // choose the best path
            bestPath = costBenefitAnalysis(closest, second, trader);

            if (bestPath != null) {
                currentPath = bestPath;
                currentPath.takePath();

                player.useBonus();
            } else {
                // If no valid paths were found, move east
                currentPath = vision.eastMostPath();
                currentPath.takePath();
            }
        }

    }

    private void equalizerStrategy() {
        Path first = null;
        Path second = null;

        // If food is below the threshold, look for the two closest food sources
        if (player.getFoodAmount() < foodThreshold) {
            System.out.println("I have a lot of water but not a lot of food. Using equalizer strategy.");
            first = vision.closestFood();
            second = vision.secondClosestFood();
        }

        // If water is below the threshold, look for the two closest water sources
        if (player.getWaterAmount() < waterThreshold) {
            System.out.println("I have a lot of food but not a lot of water. Using equalizer strategy.");
            first = vision.closestWater();
            second = vision.secondClosestWater();
        }

        // Always consider a path to the closest trader
        Path trader = vision.closestTrader();
        if (player.getGoldAmount() > goldThreshold) {
            System.out.println("I have a good amount of gold. Looking for a trader.");
        }

        // Choose the best path out of the two resources and the trader
        Path bestPath = costBenefitAnalysis(first, second, trader);

        if (bestPath != null) {
            // Move along the selected best path
            currentPath = bestPath;
            currentPath.takePath();

            // Use bonus on the tile
            player.useBonus();

        } else {
            // If no valid paths were found, move east
            currentPath = vision.eastMostPath();
            currentPath.takePath();
        }
    }

    private void lowOnOneStrategy() {
        Path p1 = null;
        Path p2 = null;

        // If food is below the threshold, get the two closest food paths
        if (player.getFoodAmount() < foodThreshold) {
            System.out.println("Low on food. Using low on one strategy.");
            p1 = vision.closestFood();
            p2 = vision.secondClosestFood();
        }

        // If water is below the threshold, get the two closest water paths
        if (player.getWaterAmount() < waterThreshold) {
            System.out.println("Low on water. Using low on one strategy.");
            p1 = vision.closestWater();
            p2 = vision.secondClosestWater();
        }

        // Choose the best path between the two resource paths (no trader considered
        // here)
        Path bestPath = costBenefitAnalysis(p1, p2, null); // null trader path

        if (bestPath != null) {
            // Move along the best path
            currentPath = bestPath;
            currentPath.takePath();

            // Use any bonus on the new tile
            player.useBonus();

        } else {
            // If no valid paths were found, move east
            currentPath = vision.eastMostPath();
            currentPath.takePath();
        }

    }

    // Take the eastmost
    // generates a east most when currentPath is null
    private void richStrategy() {
        System.out.println("I have plenty of resources. Using rich strategy.");
        currentPath = vision.eastMostPath();
        currentPath.takePath();

        // use the bonuses at the terrain the player enters
        // the path object takes care of the costs
        player.useBonus();
    }

    // compare all paths, including current one
    private Path costBenefitAnalysis(Path p1, Path p2, Path p3) {
        Path bestPath = null;
        double bestScore = 0;

        Path[] paths = { p1, p2, p3 };

        for (Path path : paths) {
            if (path != null) {
                Cost cost = path.getCost();

                // ==== Check affordability ====
                if (!player.canAfford(cost)) {
                    continue; // Skip this path if it's completely unaffordable
                }

                Terrain dest = path.getDestination();
                double score = 0;

                // ==== Evaluate Food Bonus ====
                if (dest.hasFoodBonus()) {
                    score += (player.getFoodAmount() < foodThreshold) ? 15 : 5;
                }

                // ==== Evaluate Water Bonus ====
                if (dest.hasWaterBonus()) {
                    score += (player.getWaterAmount() < waterThreshold) ? 15 : 5;
                }

                // ==== Evaluate Gold Bonus ====
                if (dest.hasGoldBonus()) {
                    score += (player.getGoldAmount() < goldThreshold) ? 15 : 5;
                }

                // ==== Evaluate Trader Bonus ====
                if (dest.hasTrader()) {
                    boolean needsToTrade = player.getGoldAmount() >= goldThreshold &&
                            (player.getFoodAmount() < foodThreshold || player.getWaterAmount() < waterThreshold);
                    if (needsToTrade) {
                        score += 12;
                    }
                }

                // ==== Subtract movement cost penalty ====
                double penalty = cost.getEnergyCost() + cost.getFoodCost() + cost.getWaterCost();
                score -= penalty;

                // ==== Keep highest scoring path ====
                if (bestPath == null || score > bestScore) {
                    bestPath = path;
                    bestScore = score;
                }
            }
        }

        return bestPath;
    }

}