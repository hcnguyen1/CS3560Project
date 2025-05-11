import java.util.Scanner;
import difficulty.Difficulty;
import difficulty.Easy;
import difficulty.Normal;
import difficulty.Hard;
import player.Player;
import terrain.Terrain;
import trader.Trader;

public class Helper {
    // for clean and abstracted coding i moved the configurations in this
    // helper.java
    public static Object[] getMapConfiguration() {
        Scanner sc = new Scanner(System.in);

        // Prompt user for map size
        System.out.print("Enter map width: ");
        int width = sc.nextInt();
        System.out.print("Enter map height: ");
        int height = sc.nextInt();

        // Prompt user for difficulty
        Difficulty difficulty = null;
        int diff = 0;

        System.out.print("Select the difficulty Level: " +
                "\n1. Easy \n2. Normal \n3. Hard\n");

        while (difficulty == null) {

            diff = sc.nextInt();

            switch (diff) {
                case 1:
                    difficulty = new Easy();
                    System.out.println("You selected Easy difficulty.");
                    break;
                case 2:
                    difficulty = new Normal();
                    System.out.println("You selected Normal difficulty.");
                    break;
                case 3:
                    difficulty = new Hard();
                    System.out.println("You selected Hard difficulty.");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }

        sc.close();

        // Return both the map dimensions and the difficulty
        return new Object[] { width, height, difficulty };
    }

    public static void printTerrainBonuses(Player player, Terrain t) {
        if (t.hasGoldBonus()) {
            System.out.println("\nThere is gold here!");
            player.useBonus();
        } else if (t.hasWaterBonus()) {
            System.out.println("\nThere is water here!");
            player.useBonus();
        } else if (t.hasFoodBonus()) {
            System.out.println("\nThere is food here!");
            player.useBonus();
        } else {
            System.out.println("\nNo usable resources here...");
        }

    }

    public static boolean checkTrader(Terrain t) {
        if (t.hasTrader()) {
            Trader trader = t.getTrader();
            String type;
            if (trader instanceof trader.FoodTrader) {
                type = "Food Trader";
            } else if (trader instanceof trader.WaterTrader) {
                type = "Water Trader";
            } else {
                type = "Gold Trader";
            }

            System.out.println("\nA " + type + " is here! You can trade resources with them.");
            return true;
        } else {
            return false;
        }
    }

    public static void interactWithTrader(Player player, Terrain t) {

        // the type of trader is now assigned here.
        Trader trader = t.getTrader();
        boolean flag = true; // check if player has enough resources to trade
        boolean offer = true;
        int offerCounter = 2;
        int newFood, newGold, newWater;

        // this if else statement checks if the user has the corresponding resources
        // that the current type of trader is looking for.
        if ("Water".equals(trader.getType())) {
            if (player.getFoodAmount() < 2) {
                if (player.getGoldAmount() < 2) {
                    System.out.println("I just checked my bag and I don't have any resources to spare.");
                    flag = false;
                }
            }
        } else if ("Gold".equals(trader.getType())) {
            if (player.getFoodAmount() < 2) {
                if (player.getWaterAmount() < 2) {
                    System.out.println("I just checked my bag and I don't have any resources to spare.");
                    flag = false;
                }
            }

        } else if ("Food".equals(trader.getType())) {
            if (player.getGoldAmount() < 2) {
                if (player.getWaterAmount() < 2) {
                    System.out.println("I just checked my bag and I don't have any resources to spare.");
                    flag = false;
                }
            }
        }

        while (flag) {

            System.out.println("Trying to trade...");
            if (Math.random() < 0.5) { // coin flip
                offer = false;
            }

            if (offer == true && flag == true) {
                System.out.println("Trader has accepted your trade.");
                // whatever type of trader, the trade will go through

                if ("Water".equals(trader.getType())) {
                    // subtract the currencies requested
                    int num = (int) (Math.random() * 2) + 1;

                    switch (num) {
                        case 1:
                            System.out.println("traded 2 Food for 1 Water");
                            newFood = player.getFoodAmount() - 2;
                            player.food = newFood;
                            player.water++;
                            break;
                        case 2:
                            System.out.println("traded 2 Gold for 1 Water");
                            newGold = player.getGoldAmount() - 2;
                            player.gold = newGold;
                            player.water++;
                            break;
                    }
                } else if ("Gold".equals(trader.getType())) {
                    // subtract the currencies requested
                    int num = (int) (Math.random() * 2) + 1;

                    switch (num) {
                        case 1:
                            System.out.println("traded 2 Food for 1 Gold");
                            newFood = player.getFoodAmount() - 2;
                            player.food = newFood;
                            player.gold++;
                            break;
                        case 2:
                            System.out.println("traded 2 Water for 1 Gold");
                            newWater = player.getWaterAmount() - 2;
                            player.water = newWater;
                            player.gold++;
                            break;
                    }

                } else if ("Food".equals(trader.getType())) {
                    // subtract the currencies requested
                    int num = (int) (Math.random() * 2) + 1;

                    switch (num) {
                        case 1:
                            System.out.println("traded 2 Water for 1 Food");
                            newWater = player.getWaterAmount() - 2;
                            player.water = newWater;
                            player.food++;
                            break;
                        case 2:
                            System.out.println("traded 2 Gold for 1 Food");
                            newGold = player.getGoldAmount() - 2;
                            player.gold = newGold;
                            player.food++;
                            break;
                    }
                }

                offerCounter--;

            } else {
                System.out.println("Trader has rejected your proposal and has given a counter offer.");

                if (Math.random() < 0.5) { // coin flip
                    System.out.println("Player: I must decline.");
                    flag = false;
                } else {

                    if ("Water".equals(trader.getType())) {
                        if (player.getFoodAmount() < 3) {
                            if (player.getGoldAmount() < 3) {
                                System.out
                                        .println("I just checked my bag and I don't have any resources to spare.");
                                flag = false;
                            }
                        }
                    } else if ("Gold".equals(trader.getType())) {
                        if (player.getFoodAmount() < 3) {
                            if (player.getWaterAmount() < 3) {
                                System.out
                                        .println("I just checked my bag and I don't have any resources to spare.");
                                flag = false;
                            }
                        }
                    } else if ("Food".equals(trader.getType())) {
                        if (player.getGoldAmount() < 3) {
                            if (player.getWaterAmount() < 3) {
                                System.out
                                        .println("I just checked my bag and I don't have any resources to spare.");
                                flag = false;
                            }
                        }

                        System.out.println("Player: Sounds good to me.");
                    }

                    if ("Water".equals(trader.getType()) && flag == true) {
                        // subtract the currencies requested
                        int num = (int) (Math.random() * 2) + 1;

                        switch (num) {
                            case 1:
                                System.out.println("traded 3 Food for 1 Water");
                                newFood = player.getFoodAmount() - 3;
                                player.food = newFood;
                                player.water++;
                                break;
                            case 2:
                                System.out.println("traded 3 Gold for 1 Water");
                                newGold = player.getGoldAmount() - 3;
                                player.gold = newGold;
                                player.water++;
                                break;
                        }
                    } else if ("Gold".equals(trader.getType()) && flag == true) {
                        // subtract the currencies requested
                        int num = (int) (Math.random() * 2) + 1;

                        switch (num) {
                            case 1:
                                System.out.println("traded 3 Food for 1 Gold");
                                newFood = player.getFoodAmount() - 3;
                                player.food = newFood;
                                player.gold++;
                                break;
                            case 2:
                                System.out.println("traded 3 Water for 1 Gold");
                                newWater = player.getWaterAmount() - 3;
                                player.water = newWater;
                                player.gold++;
                                break;
                        }
                    } else if ("Food".equals(trader.getType()) && flag == true) {

                        // subtract the currencies requested
                        int num = (int) (Math.random() * 2) + 1;

                        switch (num) {
                            case 1:
                                System.out.println("traded 3 Water for 1 Food");
                                newWater = player.getWaterAmount() - 3;
                                player.water = newWater;
                                player.food++;
                                break;
                            case 2:
                                System.out.println("traded 3 Gold for 1 Food");
                                newGold = player.getGoldAmount() - 3;
                                player.gold = newGold;
                                player.food++;
                                break;
                        }
                    }

                    offerCounter--;
                }
                if (offerCounter == 0) {
                    flag = false;
                }
            }
        }

    }
}