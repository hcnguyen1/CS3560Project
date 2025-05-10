package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import terrain.*;

public abstract class Vision {

  // ==== Fields ====
  protected Player player;

  // ==== Constructor ====
  public Vision(Player p) {
    player = p;
  }

  // ==== Path Generation ====

  //returns the list of all paths the vision can see
  public List<Path> getAllPaths() {
    List<Path> paths = new ArrayList<>();

    int x = player.getX() + 1;
    int y = player.getY();

    addPath(paths, x, y);

    return paths;
  }

  protected Path addPath(List<Path> p, int x, int y) {
    Path path = new Path(player);
    if (path.setNextCoord(x, y)) {
      p.add(path);
      return path;
    }
    return null;
  }

  // Returns all lowest movement cost paths
  public List<Path> easiestPath() {
    List<Path> paths = this.getAllPaths();
    if (paths == null || paths.isEmpty()) {
      return null;
    }

    List<Path> filtered = new ArrayList<>(paths);
    // Sort by movement cost
    filtered.sort(Comparator.comparingInt(p -> p.getCost().getEnergyCost()));
    int minCost = filtered.get(0).getCost().getEnergyCost();

    //remove all paths with a greater movement cost
    for (int i = 1; i < filtered.size(); i++) {
      if (filtered.get(i).getCost().getEnergyCost() > minCost) {
        filtered.remove(i);
        i--;
      }
    }

    // Sort by farthest east
    filtered.sort(Comparator.comparingInt(p -> p.getStepX()));
    // filtered.reversed();
    Collections.reverse(filtered);

    return filtered;
  }

  public Path eastMostPath() {
    Path path = new Path(player);
    int x = player.getX() + 1;
    int y = player.getY();
    if (path.setNextCoord(x, y)) {
      return path;
    } else {
      return null;
    }
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
  public Path compareClosestPath(List<Path> paths, int rank, String type) {
    if (type.equalsIgnoreCase("easiest")) {
      List<Path> easiest = easiestPath();
      if (easiest.isEmpty()) return null;
      if (easiest.size() == 1) return easiest.get(0);

      // sort by most east
      easiest.sort(Comparator.comparingInt(p -> p.getStepX()));

      return easiest.get(easiest.size() - 1);
    }

    // Filter paths by resource type
    List<Path> filtered = new ArrayList<>();
    for (int i = 0; i < paths.size(); i++) {
      Terrain dest = paths.get(i).getDestination();
      if (matchesType(dest, type)) {
        filtered.add(paths.get(i));
      }
    }

    // Sort by movement cost
    filtered.sort(Comparator.comparingInt(p -> p.getCost().getEnergyCost()));
    int minCost = filtered.get(0).getCost().getEnergyCost();

    //remove all paths with a greater movement cost
    for (int i = 1; i < filtered.size(); i++) {
      if (filtered.get(i).getCost().getEnergyCost() > minCost) {
        filtered.remove(i);
        i--;
      }
    }

    // Sort by farthest east
    filtered.sort(Comparator.comparingInt(p -> p.getStepX()));
    // filtered.reversed();
    Collections.reverse(filtered);

    return (rank <= filtered.size()) ? filtered.get(rank - 1) : null;
  }

  // Returns the second best path by cost, prefers farther east if tied
  public Path compare2ndClosestPath(
    List<Path> paths,
    int numPath,
    String type
  ) {
    List<Path> filtered = new ArrayList<>();

    for (int i = 0; i < paths.size(); i++) {
      Terrain dest = paths.get(i).getDestination();
      if (matchesType(dest, type)) {
        filtered.add(paths.get(i));
      }
    }

    int size = filtered.size();
    if (size == 0) return null;
    if (size == 1) return filtered.get(0);

    // Sort by movement cost
    filtered.sort(Comparator.comparingInt(p -> p.getCost().getEnergyCost()));
    int min2Cost = filtered.get(1).getCost().getEnergyCost();

    //remove all paths with a greater movement cost
    for (int i = 1; i < filtered.size(); i++) {
      if (filtered.get(i).getCost().getEnergyCost() > min2Cost) {
        filtered.remove(i);
        i--;
      }
    }

    size = filtered.size();
    if (size == 1) return filtered.get(0);

    // Sort by farthest east
    filtered.sort(Comparator.comparingInt(p -> p.getStepX()));
    // filtered.reversed();
    Collections.reverse(filtered);

    return filtered.get(1);
  }

  // ==== Internal Helper ====

  // Checks if a terrain tile contains a specific resource type
  private boolean matchesType(Terrain tile, String type) {
    switch (type.toLowerCase()) {
      case "food":
        return tile.hasFoodBonus();
      case "water":
        return tile.hasWaterBonus();
      case "gold":
        return tile.hasGoldBonus();
      case "trader":
        return tile.hasTrader();
      default:
        return false;
    }
  }
}
