package terrain;

import java.util.Random;

import bonus.Bonus;

public class Desert extends Terrain {

  private static final int MOVEMENT_COST = 3;
  private static final int WATER_COST = 3;
  private static final int FOOD_COST = 3;
  private static final float SPAWN_RATE = 0.2f;
  private static final String NAME = "Desert";
  private static final Random r = new Random();

  public Desert() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public Bonus getBonus() {
    return null;
  }

  private static final String[] desertTexts = new String[] {
      "The scorching sun beats down on endless waves of sand, where heat shimmers and mirages deceive the weary traveler.",
      "Endless dunes stretch beneath a blazing sun, with heat waves and illusions challenging every step.",
      "The desert's relentless sun bakes the shifting sands, while distant mirages lure the exhausted onward.",
      "Beneath the merciless sun, rolling dunes of sand ripple endlessly, with heat and mirages testing your resolve.",
      "The blistering heat radiates from vast sands, where shimmering mirages and dry winds test your endurance.",
      "Endless golden dunes shift beneath a burning sun, with heat waves and illusions playing tricks on the eyes.",
      "The desert sun scorches the barren sands, while wavering mirages tempt the parched traveler forward.",
      "Baking heat radiates from the endless dunes, where shimmering mirages and dry winds challenge your journey.",
      "The relentless sun blazes over shifting sands, with heat waves and distant mirages testing your strength.",
      "Under the fierce sun, endless dunes of sand stretch out, with heat and mirages playing tricks on the weary."
  };

  @Override
  public void getStory() {
    System.out.println(desertTexts[r.nextInt(desertTexts.length)]);
  }
}
