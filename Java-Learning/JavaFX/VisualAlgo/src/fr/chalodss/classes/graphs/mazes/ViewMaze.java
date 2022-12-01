package fr.chalodss.classes.graphs.mazes;

import static fr.chalodss.utils.Constants.BFS;
import static fr.chalodss.utils.Constants.DFS;
import static fr.chalodss.utils.Constants.DIJKSTRA;
import java.util.Set;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Charles T.
 * 
 */
public final class ViewMaze {

  private static GraphicsContext gcGrid;
  private static GraphicsContext gcMazeLayer;
  private static Color           color;
  private static Timeline        timeLine;
  private static double          cellSize;

  private ViewMaze() {

  }

  static void drawPath(Set<Cell> path, double time) {
    if (!path.isEmpty()) {
      var iterator = path.iterator();
      timeLine = new Timeline(new KeyFrame(Duration.millis(time), e -> {
        var cell = iterator.next();
        drawCell(cell);
      }));
      timeLine.setCycleCount(path.size());
      timeLine.play();
    }
  }

  static void drawGrid() {
    gcGrid.setStroke(Color.BLACK);
    gcGrid.setLineWidth(1);
    IntStream.range(1, (int) (1280 / cellSize)).forEach(i -> gcGrid.strokeLine(i * cellSize, 0, i * cellSize, 720));
    IntStream.range(1, (int) (720 / cellSize)).forEach(j -> gcGrid.strokeLine(0, j * cellSize, 1280, j * cellSize));
  }

  static void drawCell(Cell cell) {
    gcMazeLayer.setFill(color);
    gcMazeLayer.fillRect(cell.y * cellSize, cell.x * cellSize, cellSize, cellSize);
  }

  static void drawMaze(MazeGrid2D maze) {
    for (var i = 0; i < maze.height; i++) {
      for (var j = 0; j < maze.width; j++) {
        setCellColor(maze.data[i][j].type);
        drawCell(maze.data[i][j]);
      }
    }
  }

  static void reset(double w, double h) {
    if (timeLine != null) {
      timeLine.stop();
    }
    gcMazeLayer.clearRect(0, 0, w, h);
    gcGrid.clearRect(0, 0, w, h);
  }

  static void setGcGrid(GraphicsContext gcGrid) {
    ViewMaze.gcGrid = gcGrid;
  }

  static void setGcMazeLayer(GraphicsContext gcMazeLayer) {
    ViewMaze.gcMazeLayer = gcMazeLayer;
  }

  static void setCellSize(int cellSize) {
    ViewMaze.cellSize = cellSize;
  }

  static void setAlgoColor(String algorithm) {
    color = switch (algorithm) {
      case BFS      -> Color.web("#1DE9B6");
      case DFS      -> Color.web("#90CAF9");
      case DIJKSTRA -> Color.web("#E040FB");
      default       -> throw new IllegalArgumentException();
    };
  }

  static void setCellColor(ECell cell) {
    color = switch (cell) {
      case START    -> Color.web("#76FF03");
      case END      -> Color.web("#FF8F00");
      case PATH     -> Color.web("#566573");
      case GRASS    -> Color.web("#27AE60");
      case MOUNTAIN -> Color.web("#6E2C00");
      case WALL     -> Color.web("#263238");
      case WATER    -> Color.web("#3498DB");
      default       -> throw new IllegalArgumentException();
    };
  }

}
