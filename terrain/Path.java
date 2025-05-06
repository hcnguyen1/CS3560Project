package terrain;

import map.Map;
import player.Player;

public class Path {

  private Cost costs;
  private int[] movesX = new int[2];
  private int[] movesY = new int[2];
  // private int numSteps = 0;
  // private int nextStep = 0;
  private int pathLength = 0; // replace numSteps with pathLength
  private int currentStep = 0; // replace nextStep with currentStep
  private Player player;

  public Path(Player p) {
    player = p;
    costs = new Cost(0, 0, 0); // Initialize costs with zero values
  }

  public boolean setNextCoord(int Xcoord, int Ycoord) {
    Terrain t = Map.getInstance().getTerrain(Xcoord, Ycoord);
    if (t != null) {
      movesX[pathLength] = Xcoord;
      movesY[pathLength] = Ycoord;
      // nextStep++;
      pathLength++;
      costs.add(t.getCost());
      return true;
    } else {
      return false;
    }
  }

  public int getNumSteps() {
    return pathLength - currentStep;
  }

  public int getStepX() {
    return movesX[currentStep];
  }

  public Cost getCost() {
    return costs;
  }

  public void takePath() {
    //only moves the player if there is a next step to go to
    if (currentStep < pathLength) {
      //move the player to the next block on the path
      player.setNextCoord(movesX[currentStep], movesY[currentStep]);
      //use the costs to move there
      Terrain t = player.getCurrentTerrain();
      player.useCost(t.getCost());
      currentStep++;
    }
  }

  public Terrain getDestination() {
    return Map
      .getInstance()
      .getTerrain(movesX[currentStep], movesY[currentStep]);
  }
}
