import java.util.Scanner; 
import difficulty.Difficulty; 
import difficulty.Easy; 
import difficulty.Normal; 
import difficulty.Hard; 

public class Helper {
    // for clean and abstracted coding i moved the configurations in this helper.java
    public static Object[] getMapConfiguration() {
        Scanner sc = new Scanner(System.in);

        // Prompt user for map size
        System.out.print("Enter map width: ");
        int width = sc.nextInt();
        System.out.print("Enter map height: ");
        int height = sc.nextInt();

        // Prompt user for difficulty
        System.out.print("Select the difficulty Level: " +
                "\n1. Easy \n2. Normal \n3. Hard\n");
        int diff = sc.nextInt();

        Difficulty difficulty = null;

        // Handle difficulty selection
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

        sc.close();

        // Return both the map dimensions and the difficulty
        return new Object[] { width, height, difficulty };
    }
}
