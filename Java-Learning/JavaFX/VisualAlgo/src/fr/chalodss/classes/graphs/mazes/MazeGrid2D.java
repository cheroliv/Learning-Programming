package fr.chalodss.classes.graphs.mazes;

import static fr.chalodss.classes.graphs.mazes.ECell.PATH;
import static fr.chalodss.classes.graphs.mazes.ECell.WALL;
import static fr.chalodss.utils.Constants.BFS;
import static fr.chalodss.utils.Constants.DFS;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author Charles T.
 * 
 */
public final class MazeGrid2D implements Callable<Boolean> {

  Cell[][]  data;
  int       heigth;
  int       width;
  Cell      start;
  Cell      end;
  Set<Cell> path;
  String    algorithm;


  public MazeGrid2D(int width, int heigth, double prob) {
    this.data   = new Cell[heigth][width];
    this.heigth = heigth;
    this.width  = width;
    this.path   = new LinkedHashSet<>();

    for (var i = 0; i < heigth; i++) {
      for (var j = 0; j < width; j++) {
        if (Math.random() < prob) {
          data[i][j] = new Cell(WALL, i, j);
        } else {
          data[i][j] = new Cell(PATH, i, j);
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
    for (var i = 0; i < heigth; i++) {
      for (var j = 0; j < width; j++) {
        var cell = data[i][j];
        if (i - 1 > -1)     addAdjCell(cell.adjCells, data[i - 1][j]);
        if (j - 1 > -1)     addAdjCell(cell.adjCells, data[i][j - 1]);
        if (i + 1 < heigth) addAdjCell(cell.adjCells, data[i + 1][j]);
        if (j + 1 < width)  addAdjCell(cell.adjCells, data[i][j + 1]);
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

  boolean isMazeInitialized() {
    return start != null && end != null && path.isEmpty();
  }

  void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  @Override
  public Boolean call() throws Exception {
    return switch (algorithm) {
      case DFS -> depthFirstSearch();
      case BFS -> breadthFirstSearch();
      default  -> false;
    };
  }

}
