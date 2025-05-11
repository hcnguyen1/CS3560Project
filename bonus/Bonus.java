package bonus;

import bonus.ResourceBonus.ResourceType;
// import trader.ResourceBundle;
import player.Player;

public interface Bonus {
  // Getters for the bonus values
  public int getGold();

  public int getWater();

  public int getFood();

  // Method for getting the resources gained when used
  public void useBonus(Player player);
}
