package fr.chalodss.classes.graphs.mazes;

import static fr.chalodss.utils.Constants.BFS;
import static fr.chalodss.utils.Constants.DFS;

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
  private static double          dim;

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
    IntStream.range(1, (int) (1280 / dim)).forEach(i -> gcGrid.strokeLine(i * dim, 0, i * dim, 720));
    IntStream.range(1, (int) (720 / dim)).forEach(j -> gcGrid.strokeLine(0, j * dim, 1280, j * dim));
  }

  static void drawCell(Cell cell) {
    gcMazeLayer.setFill(color);
    gcMazeLayer.fillRect(cell.y * dim, cell.x * dim, dim, dim);
  }

  static void drawMaze(MazeGrid2D maze) {
    for (var i = 0; i < maze.heigth; i++) {
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

  static void setDim(int dim) {
    ViewMaze.dim = dim;
  }

  static void setAlgoColor(String algorithm) {
    color = switch (algorithm) {
      case BFS -> Color.web("#1DE9B6");
      case DFS -> Color.web("#90CAF9");
      default  -> throw new IllegalArgumentException();
    };
  }

  static void setCellColor(ECell cell) {
    color = switch (cell) {
      case END -> Color.web("#FF8F00");
      case PATH -> Color.web("#566573");
      case START -> Color.web("#76FF03");
      case WALL -> Color.web("#263238");
      default -> throw new IllegalArgumentException();
    };
  }

}
