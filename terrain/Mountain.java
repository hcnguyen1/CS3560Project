package terrain;

import java.util.Random;

import bonus.Bonus;
import bonus.ResourceBonus.ResourceType;
import bonus.ResourceBonus;

public class Mountain extends Terrain {

  private static final int MOVEMENT_COST = 3;
  private static final int WATER_COST = 2;
  private static final int FOOD_COST = 2;
  private static final float SPAWN_RATE = 0.1f;
  private static final String NAME = "Mountain";
  private static final int BONUS_GOLD_AMOUNT = 2;
  private static final Random r = new Random();

  public Mountain() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public Bonus getBonus() {
    return new ResourceBonus(ResourceType.GOLD, BONUS_GOLD_AMOUNT, false);
  }

  @Override
  public boolean hasGoldBonus() {
    return true;
  }

  private static final String[] mountainTexts = new String[] {
      "Jagged rocky peaks soar into the mist, with biting winds and sheer drops challenging every step.",
      "The mountain's steep cliffs rise into swirling clouds, where icy gusts and treacherous paths test your resolve.",
      "Rocky slopes climb sharply into the sky, with cold winds and dizzying heights demanding careful footing.",
      "Sharp peaks pierce the clouds, while icy winds howl past sheer cliffs and every step risks a deadly fall.",
      "The mountain rises steep and rugged, with chilling winds and narrow paths that reveal stunning yet dangerous views.",
      "Craggy slopes ascend into the clouds, where cold air bites and every step teeters on the edge of a precipice.",
      "The mountain's rocky face climbs steeply, buffeted by icy winds and shadowed by sheer drops below.",
      "Steep cliffs and jagged peaks reach into the clouds, where cold gusts and perilous paths challenge the brave.",
      "The mountain's towering slopes are carved from stone, with icy winds and sheer drops demanding caution.",
      "Rugged peaks rise sharply into the mist, where biting cold and narrow ledges test your every move."
  };

  @Override
  public void getStory() {
    System.out.println(mountainTexts[r.nextInt(mountainTexts.length)]);
  }

}
