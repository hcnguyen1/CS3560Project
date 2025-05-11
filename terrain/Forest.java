package terrain;

import java.util.Random;

import bonus.Bonus;
import bonus.ResourceBonus.ResourceType;
import bonus.ResourceBonus;

public class Forest extends Terrain {

  private static final int MOVEMENT_COST = 2;
  private static final int WATER_COST = 1;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.3f;
  private static final String NAME = "Forest";
  private static final int BONUS_FOOD_AMOUNT = 2;
  private static final Random r = new Random();

  public Forest() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public Bonus getBonus() {
    return new ResourceBonus(ResourceType.FOOD, BONUS_FOOD_AMOUNT, false);
  }

  @Override
  public boolean hasFoodBonus() {
    return true;
  }

  private static final String[] forestTexts = new String[] {
      "Sunlight filters through the tall trees, casting dancing shadows on the vibrant undergrowth alive with the sounds of wildlife.",
      "In the forest, beams of light pierce the canopy, revealing a lively undergrowth filled with rustling leaves and distant animal calls.",
      "The forest is alive with flickering sunlight, rustling leaves, and the faint calls of creatures hidden among the trees.",
      "Towering trees let sunlight through in patches, lighting up the lush forest floor where wildlife stirs in the shadows.",
      "Sunlight dapples through the dense forest canopy, illuminating the vibrant undergrowth and the distant sounds of animals.",
      "The forest glows with scattered sunlight, alive with the rustle of leaves and the calls of unseen creatures.",
      "Light filters through the towering trees, casting lively shadows on the forest floor teeming with hidden wildlife.",
      "In the forest, sunlight dances through the leaves, revealing a vibrant undergrowth filled with the sounds of life.",
      "The forest canopy parts to let sunlight flicker down onto the lush undergrowth, alive with rustling leaves and distant calls.",
      "Sunlight breaks through the tall trees, illuminating a forest floor bustling with the quiet movements of hidden animals."
  };

  @Override
  public void getStory() {
    System.out.println(forestTexts[r.nextInt(forestTexts.length)]);
  }
}
