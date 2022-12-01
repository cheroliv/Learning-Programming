package fr.chalodss.classes.graphs.mazes;

import static fr.chalodss.classes.graphs.mazes.ECell.START;
import static fr.chalodss.classes.graphs.mazes.ECell.END;
import static fr.chalodss.classes.graphs.mazes.ECell.PATH;
import static fr.chalodss.classes.graphs.mazes.ECell.WALL;
import static fr.chalodss.utils.Constants.BFS;
import static fr.chalodss.utils.Constants.DFS;
import static fr.chalodss.utils.Constants.DIJKSTRA;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author Charles T.
 * 
 */
public final class MazeGrid2D implements Callable<Boolean> {

  Cell[][]  data;
  int       height;
  int       width;
  Cell      start;
  Cell      end;
  Set<Cell> path;
  String    algorithm;


  public MazeGrid2D(int width, int height, Cell[][] data) {
    this.data   = data;
    this.height = height;
    this.width  = width;
    this.path   = new LinkedHashSet<>();
  }

  public MazeGrid2D(int width, int height, double prob) {
    this.data   = new Cell[height][width];
    this.height = height;
    this.width  = width;
    this.path   = new LinkedHashSet<>();

    for (var i = 0; i < height; i++) {
      for (var j = 0; j < width; j++) {
        if (Math.random() < prob) {
          data[i][j] = new Cell(WALL, i, j, 0);
        } else {
          data[i][j] = new Cell(PATH, i, j, 1);
        }
      }
    }
  }

  void addAdjCell(List<Cell> adjList, Cell cell) {
    if (cell.type != WALL) {
      adjList.add(cell);
    }
  }

  void setAdjacentCells() {
    for (var i = 0; i < height; i++) {
      for (var j = 0; j < width; j++) {
        var cell = data[i][j];
        if (i - 1 > -1)
          addAdjCell(cell.adjCells, data[i - 1][j]);
        if (j - 1 > -1)
          addAdjCell(cell.adjCells, data[i][j - 1]);
        if (i + 1 < height)
          addAdjCell(cell.adjCells, data[i + 1][j]);
        if (j + 1 < width)
          addAdjCell(cell.adjCells, data[i][j + 1]);
      }
    }
  }

  Boolean depthFirstSearch() {
    var cell  = start;
    var stack = new ArrayDeque<Cell>();

    stack.push(cell);
    while (!stack.isEmpty()) {
      cell = stack.pop();
      if (!cell.equals(end)) {
        path.add(cell);
        for (Cell adjCell : cell.adjCells) {
          if (!adjCell.visited) {
            adjCell.visited = true;
            stack.push(adjCell);
          }
        }
      } else {
        break;
      }
    }
    return cell.equals(end);
  }

  Boolean breadthFirstSearch() {
    var cell  = start;
    var queue = new ArrayDeque<Cell>();

    queue.offer(cell);
    while (!queue.isEmpty()) {
      cell = queue.poll();
      if (!cell.equals(end)) {
        path.add(cell);
        for (Cell adjCell : cell.adjCells) {
          if (!adjCell.visited) {
            adjCell.visited = true;
            queue.offer(adjCell);
          }
        }
      } else {
        break;
      }
    }
    return cell.equals(end);
  }

  public void buildPath(Comparator<Cell> comparator) {
    if (end.visited) {
      var cell = end;
      while (!cell.equals(start)) {
        var min = cell.adjCells.stream().min(comparator);
        if (min.isPresent()) {
          cell = min.get();
          path.add(cell);
        }
      }
    }
  }

  public Boolean dijkstraSearch() {
    Comparator<Cell> comp  = (c1, c2) -> (int) (c1.distance - c2.distance);
    var              cell  = start;
    var              queue = new PriorityQueue<Cell>(comp);

    queue.offer(cell);
    while (!queue.isEmpty()) {
      cell = queue.poll();
      for (Cell adjCell : cell.adjCells) {
        if (cell.distance + adjCell.weight < adjCell.distance) {
          adjCell.distance = cell.distance + adjCell.weight;
        }
        if (!adjCell.visited) {
          queue.offer(adjCell);
          adjCell.visited = true;
        }
      }
    }
    buildPath(comp);
    return end.visited;
  }

  boolean isInitialized() {
    return start != null && end != null && path.isEmpty();
  }

  void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  void setStart() {
    for (Cell[] row : data) {
      for (Cell cell : row) {
        if (cell.type == START) {
          start          = cell;
          start.distance = 0;
          break;
        }
      }
    }
  }

  void setEnd() {
    for (Cell[] row : data) {
      for (Cell cell : row) {
        if (cell.type == END) {
          end = cell;
          break;
        }
      }
    }
  }

  @Override
  public Boolean call() throws Exception {
    return switch (algorithm) {
      case DFS      -> depthFirstSearch();
      case BFS      -> breadthFirstSearch();
      case DIJKSTRA -> dijkstraSearch();
      default       -> false;
    };
  }

}
