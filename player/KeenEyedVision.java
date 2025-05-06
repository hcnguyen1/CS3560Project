package player;

import java.util.List;
import terrain.Path;

public class KeenEyedVision extends CautiousVision {

  public KeenEyedVision(Player p) {
    super(p);
  }

  @Override
  public List<Path> getAllPaths() {
    //check east, northeast, and southeast
    List<Path> paths = super.getAllPaths();

    //adds a north path to the list
    this.checkNorth(paths);

    //adds a south path to the list
    this.checkSouth(paths);

    //adds east2 path to the list
    this.checkEast2(paths);

    return paths;
  }

  private void checkNorth(List<Path> p) {
    int x = player.getX();
    int y = player.getY() + 1;

    addPath(p, x, y);
  }

  private void checkSouth(List<Path> p) {
    int x = player.getX();
    int y = player.getY() - 1;

    addPath(p, x, y);
  }

  private void checkEast2(List<Path> p) {
    int x = player.getX() + 1;
    int y = player.getY();

    //create the path
    Path path = addPath(p, x, y);
    if (path != null) {
      x += 1;

      //set the next step of the path
      path.setNextCoord(x, y);
    }
  }
}
