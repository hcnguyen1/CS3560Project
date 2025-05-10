import java.util.Scanner;
import difficulty.Difficulty;
import difficulty.Easy;
import difficulty.Normal;
import difficulty.Hard;
import player.Player;
import terrain.Terrain;
import trader.Trader;
import trader.ResourceBundle;

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
        } else if (t.hasWaterBonus()) {
            System.out.println("\nThere is water here!");
        } else if (t.hasFoodBonus()) {
            System.out.println("\nThere is food here!");
        } else {
            System.out.println("\nNo usable resources here...");
        }
    
        player.useBonus();
    }

    public static void checkTrader(Terrain t) {
        if (t.hasTrader()) {
            trader.Trader trader = t.getTrader();
            String type;
            if (trader instanceof trader.FoodTrader) {
                type = "Food Trader";
            } else if (trader instanceof trader.WaterTrader) {
                type = "Water Trader";
            } else {
                type = "Gold Trader";
            }
            
            System.out.println("\nA " + type + " is here! You can trade resources with them.");
        }
    }

    public static void interactWithTrader(Player player, Terrain t) {

        ResourceBundle ob

        if (trader.isOfferAcceptable(offer) {
            System.out.println("Accepted.");
            player.
        })
        
    }


}
