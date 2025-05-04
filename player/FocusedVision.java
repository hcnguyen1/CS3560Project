package player;

import java.util.List;
import terrain.Path;

public class FocusedVision extends Vision{

    public FocusedVision(Player p) {
        super(p);
    }

    @Override
    public List<Path> getAllPaths() {
        //check east
        List<Path> paths = super.getAllPaths();

        //adds a northeast path to the list
        this.checkNorthEast(paths);

        //adds a southeast path to the list
        this.checkSouthEast(paths);

        return paths;
    }

    private void checkNorthEast(List<Path> p) {
        int x = player.getX() + 1;
        int y = player.getY() + 1;

        addPath(p, x, y);
    }

    private void checkSouthEast(List<Path> p) {
        int x = player.getX() + 1;
        int y = player.getY()-1;

        addPath(p, x, y);
    }
}
