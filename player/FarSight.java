package player;

import java.util.List;
import terrain.Path;

public class FarSight extends KeenEyedVision{
    public FarSight(Player p) {
        super(p);
    }

    @Override
    public List<Path> getAllPaths() {
        //check east, northeast, southeast, north, south, east2
        List<Path> paths = super.getAllPaths();

        //adds a north2 path to the list
        this.checkNorth2(paths);

        //adds a south2 path to the list
        this.checkSouth2(paths);

        //adds northEastNorth path to the list
        this.checkNorthEastNorth(paths);

        //adds northEastEast path to the list
        this.checkNorthEastEast(paths);

        //adds southEastSouth path to the list
        this.checkSouthEastSouth(paths);

        //adds southEastEast path to the list
        this.checkSouthEastEast(paths);

        return paths;
    }

    private void checkNorth2(List<Path> p) {
        int x = player.getX();
        int y = player.getY()+1;

        //create the path
        Path path = addPath(p, x, y);
        y+= 1;

        //set the next step of the path
        path.setNextCoord(x, y);
    }

    private void checkSouth2(List<Path> p) {
        int x = player.getX();
        int y = player.getY()-1;

        //create the path
        Path path = addPath(p, x, y);
        y-= 1;

        //set the next step of the path
        path.setNextCoord(x, y);
    }

    private void checkNorthEastNorth(List<Path> p) {
        int x = player.getX()+1;
        int y = player.getY()+1;

        //create the path
        Path path = addPath(p, x, y);
        y+= 1;

        //set the next step of the path
        path.setNextCoord(x, y);
    }

    private void checkNorthEastEast(List<Path> p) {
        int x = player.getX()+1;
        int y = player.getY()+1;

        //create the path
        Path path = addPath(p, x, y);
        x+= 1;

        //set the next step of the path
        path.setNextCoord(x, y);
    }

    private void checkSouthEastSouth(List<Path> p) {
        int x = player.getX()+1;
        int y = player.getY()-1;

        //create the path
        Path path = addPath(p, x, y);
        y-= 1;

        //set the next step of the path
        path.setNextCoord(x, y);
    }

    private void checkSouthEastEast(List<Path> p) {
        int x = player.getX()+1;
        int y = player.getY()-1;

        //create the path
        Path path = addPath(p, x, y);
        x+= 1;

        //set the next step of the path
        path.setNextCoord(x, y);
    }
}
