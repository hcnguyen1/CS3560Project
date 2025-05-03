package terrain;
import java.util.Map;
import player.Player;

public class Path {
    private Cost costs;
    private int[] movesX = new int[2];
    private int[] movesY = new int[2];
    private int numSteps = 0;
    private int nextStep = 0;
    private Player player;

    public Path(Player p) {
        player = p;
    }

    public void setNextCoord(int Xcoord, int Ycoord) {
        Terrain t = Map.getTerrain(Xcoord, Ycoord);
        if (t != null) {
            movesX[nextStep] = Xcoord;
            movesY[nextStep] = Ycoord;
            nextStep++;
            costs.addCost(t.getCost());
        }
    }

    public int getNumSteps() {
        return numSteps;
    }

    public int getNextCost() {
        if(numSteps+1<= nextStep) {
            return Map.getTerrain(movesX[numSteps+1],movesY[numSteps+1]).getCost();
        }

        return null;
    }

    public Cost getCost() {
        return costs;
    }

    public void takeStep() {
        //only moves the player if there is a next step to go to
        if (numSteps <= nextStep) {
            //move the player to the next block on the path
            player.setNextCoord(movesX[numSteps], movesY[numSteps]);
            numSteps++;

            //use the costs to move there
            Terrain t = player.getCurrentTerrain();
            player.useCost(t.getCost());
        }
    }
}
