package terrain;

import bonus.ResourceBonus;
import bonus.ResourceBonus.ResourceType;

import java.util.Random;

import bonus.Bonus;

public class Lake extends Terrain {

  private static final int MOVEMENT_COST = 2;
  private static final int WATER_COST = 1;
  private static final int FOOD_COST = 1;
  private static final float SPAWN_RATE = 0.3f;
  private static final String NAME = "Lake";
  private static final int BONUS_WATER_AMOUNT = 3;
  private static final Random r = new Random();

  public Lake() {
    super(MOVEMENT_COST, WATER_COST, FOOD_COST, SPAWN_RATE);
  }

  @Override
  public String getNameTerrain() {
    return this.NAME;
  }

  @Override
  public Bonus getBonus() {
    return new ResourceBonus(ResourceType.WATER, BONUS_WATER_AMOUNT, false);
  }

  @Override
  public boolean hasWaterBonus() {
    return true;
  }

  private static final String[] lakeTexts = new String[] {
      "The crystalline waters mirror towering pines along rocky shores, where sudden depth drops conceal schools of silver fish and darker, larger shapes moving below.",
      "Morning mist clings to the lake's glassy surface, broken only by jumping trout and the eerie calls of loons echoing across hidden coves.",
      "Sunken ruins peek through the turquoise depths, their moss-covered stones home to schools of curious fish and something ancient that watches from below.",
      "A glacial lake's icy waters shimmer with unnatural blue hues, its frigid temperatures preserved by surrounding snow-capped peaks that cast dagger-like reflections.",
      "Bioluminescent algae paint the midnight lake in ethereal greens, their glow revealing the silhouettes of submerged trees and darting water spirits.",
      "Steaming geothermal waters bubble from volcanic vents below, creating mineral-rich pools surrounded by vibrant microbial mats in oranges and blood-reds.",
      "The vast lake stretches to the horizon, its wave-tossed surface hiding treacherous currents that have claimed ships now resting in the lightless abyss.",
      "Lily pads large enough to stand on dot the shallows, their pink blossoms concealing venomous water snakes and schools of razor-toothed gar.",
      "A blackwater lake's tannin-stained surface perfectly mirrors the starscape above, broken only by the occasional rise of air bubbles from submerged caves.",
      "The lake's center churns with a perpetual whirlpool, its depths rumored to hold a sunken temple guarded by tentacled leviathans that surface during storms."
  };

  @Override
  public void getStory() {
    System.out.println(lakeTexts[r.nextInt(lakeTexts.length)]);
  }
}
