package player;

import terrain.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Vision {

    // ==== Fields ====
    protected int playerLocationX;
    protected int playerLocationY;
    protected Terrain[][] map;
    protected Player player;

    // ==== Constructor ====
    public Vision(Player player, Terrain[][] map) {
        this.player = player;
        this.map = map;
    }

    // ==== Basic Utility Methods ====

    // Sets the player’s current location on the map
    public void setLocation(int x, int y) {
        this.playerLocationX = x;
        this.playerLocationY = y;
    }

    // Returns the terrain at a specific x, y coordinate if it's valid
    public Terrain checkSquare(int x, int y) {
        if (x >= 0 && y >= 0 && x < map.length && y < map[0].length) {
            return map[x][y];
        }
        return null;
    }

    // ==== Path Generation ====

    // Gets all paths this Vision type can see — right now only checks 1 tile east
    @Override
    public Path[] getAllPaths() {
        List<Path> paths = new ArrayList<>();

        int x = playerLocationX + 1;
        int y = playerLocationY;
        Terrain t = checkSquare(x, y);

        if (t != null) {
            Path path = new Path(player);
            path.setNextCoord(x, y);
            paths.add(path);
        }

        return paths.toArray(new Path[0]);
    }

    // Returns all lowest-cost paths from current vision
    public Path[] easiestPath() {
        Path[] allPaths = getAllPaths();
        if (allPaths == null || allPaths.length == 0) {
            return new Path[0];
        }

        int minCost = allPaths[0].getCost();
        for (int i = 1; i < allPaths.length; i++) {
            int cost = allPaths[i].getCost();
            if (cost < minCost) {
                minCost = cost;
            }
        }

        List<Path> minPaths = new ArrayList<>();
        for (Path path : allPaths) {
            if (path.getCost() == minCost) {
                minPaths.add(path);
            }
        }

        return minPaths.toArray(new Path[0]);
    }

    // ==== Closest/Second Closest Resource Paths ====

    public Path closestFood() {
        return compareClosestPath(getAllPaths(), 1, "food");
    }

    public Path secondClosestFood() {
        return compareClosestPath(getAllPaths(), 2, "food");
    }

    public Path closestWater() {
        return compareClosestPath(getAllPaths(), 1, "water");
    }

    public Path secondClosestWater() {
        return compareClosestPath(getAllPaths(), 2, "water");
    }

    public Path closestGold() {
        return compareClosestPath(getAllPaths(), 1, "gold");
    }

    public Path secondClosestGold() {
        return compareClosestPath(getAllPaths(), 2, "gold");
    }

    public Path closestTrader() {
        return compareClosestPath(getAllPaths(), 1, "trader");
    }

    public Path secondClosestTrader() {
        return compareClosestPath(getAllPaths(), 2, "trader");
    }

    // ==== Path Comparison Methods ====

    // Finds the best matching path based on resource type and rank (1st, 2nd, etc.)
    public Path compareClosestPath(Path[] paths, int rank, String type) {
        if (type.equalsIgnoreCase("easiest")) {
            Path[] easiest = easiestPath();
            if (easiest.length == 0) return null;
            if (easiest.length == 1) return easiest[0];

            // From all lowest-cost paths, return the one farthest east
            Path maxEast = easiest[0];
            for (int i = 1; i < easiest.length; i++) {
                if (easiest[i].getStepX() > maxEast.getStepX()) {
                    maxEast = easiest[i];
                }
            }

            return maxEast;
        }

        // Filter paths by resource type
        List<Path> filtered = new ArrayList<>();
        for (Path path : paths) {
            Terrain dest = path.getDestinationTile();
            if (matchesType(dest, type)) {
                filtered.add(path);
            }
        }

        // Sort by cost and return the nth path
        filtered.sort(Comparator.comparingInt(p -> p.getCost().getTotal()));
        return (rank <= filtered.size()) ? filtered.get(rank - 1) : null;
    }

    // Returns the second best path by cost, prefers farther east if tied
    public Path compare2ndClosestPath(Path[] paths, int numPath, String type) {
        List<Path> filtered = new ArrayList<>();

        for (Path path : paths) {
            Terrain dest = path.getDestinationTile();
            if (matchesType(dest, type)) {
                filtered.add(path);
            }
        }

        int size = filtered.size();
        if (size == 0) return null;
        if (size == 1) return filtered.get(0);

        filtered.sort(Comparator.comparingInt(p -> p.getCost().getTotal()));

        Path min1 = filtered.get(0);
        Path min2 = filtered.get(1);

        if (min1.getCost().getTotal() == min2.getCost().getTotal()) {
            if (min2.getStepX() > min1.getStepX()) {
                Path temp = min1;
                min1 = min2;
                min2 = temp;
            }
        }

        return min2;
    }

    // ==== Internal Helper ====

    // Checks if a terrain tile contains a specific resource type
    private boolean matchesType(Terrain tile, String type) {
        switch (type.toLowerCase()) {
            case "food": return tile.hasFoodBonus();
            case "water": return tile.hasWaterBonus();
            case "gold": return tile.hasGoldBonus();
            case "trader": return tile.hasTrader();
            default: return false;
        }
    }
}