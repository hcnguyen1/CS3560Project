package terrain;

import java.util.Random;

import bonus.Bonus;
import bonus.ResourceBonus;
import bonus.ResourceBonus.ResourceType;

public class Plains extends Terrain {

  private static final int MOVEMENT_COST = 1;
  private static final int WATER_COST = 1;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.5f;
  private static final String NAME = "Plains";
  private static final Random r = new Random();

  public Plains() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  /*
   * getTrader() and getBonus() for Player.java to use
   * public Bonus getTrader() {
   * return null;
   * }
   */

  @Override
  public Bonus getBonus() {
    return null;
  }

  // getNameTerrain return String
  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  private static final String[] plainTexts = new String[] {
      "Endless waves of tall grass sway under a wide-open sky, where danger can approach unseen and storms roll in fast.",
      "The plains stretch far and wide, with tall grasses dancing in the wind and little cover from predators or weather.",
      "Tall grass ripples across the vast plains, where the open sky reveals both beauty and lurking threats.",
      "Under a boundless sky, the plains' golden grasses sway, offering wide views but scant shelter from danger.",
      "The open plains roll endlessly, with tall grass waving in the wind and predators lurking beyond the horizon.",
      "Sweeping grasslands stretch beneath an endless sky, where the wind carries both beauty and the threat of storms.",
      "The plains' tall grasses ripple in the breeze, exposing travelers to the elements and hidden dangers alike.",
      "Vast grasslands stretch to the horizon, where the open sky and swaying stalks offer little protection from harm.",
      "The plains are a sea of golden grass under a wide sky, where danger is visible but often unavoidable.",
      "Tall grasses sway across the endless plains, where the open landscape reveals both beauty and peril."
  };

  @Override
  public void getStory() {
    System.out.println(plainTexts[r.nextInt(plainTexts.length)]);
  }
}
