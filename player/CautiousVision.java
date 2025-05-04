package player;

import java.util.List;
import terrain.Path;

public class CautiousVision extends Vision{

    public CautiousVision(Player p) {
        super(p);
    }

    @Override
    public List<Path> getAllPaths() {
        //check east
        List<Path> paths = super.getAllPaths();

        //adds a north path to the list
        this.checkNorth(paths);

        //adds a south path to the list
        this.checkSouth(paths);

        return paths;
    }

    private void checkNorth(List<Path> p) {
        int x = player.getX();
        int y = player.getY() + 1;

        addPath(p, x, y);
    }

    private void checkSouth(List<Path> p) {
        int x = player.getX();
        int y = player.getY()-1;

        addPath(p, x, y);
    }
}
