package fr.chalodss.classes.gameoflife;

import java.util.BitSet;
import java.util.stream.IntStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Charles T.
 * 
 */
public final class ViewGOL {

  private static GraphicsContext gcGrid;
  private static GraphicsContext gcGolLayer;
  private static Color           color;
  private static double          dim;
  private static int             width;
  private static int             height;

  private ViewGOL() {

  }

  static void drawGrid() {
    gcGrid.setStroke(Color.BLACK);
    gcGrid.setLineWidth(1);
    IntStream.range(1, (int) (1280 / dim)).forEach(i -> gcGrid.strokeLine(i * dim, 0, i * dim, 720));
    IntStream.range(1, (int) (720 / dim)).forEach(j -> gcGrid.strokeLine(0, j * dim, 1280, j * dim));
  }

  static void drawCell(int x, int y) {
    gcGolLayer.setFill(color);
    gcGolLayer.fillRect(x * dim, y * dim, dim, dim);
  }

  static void drawCells(BitSet[] grid) {
    for (var i = 1; i <= height; i++) {
      for (var j = 1; j <= width; j++) {
        setCellColor(grid[i].get(j));
        drawCell(j - 1, i - 1);
      }
    }
  }

  static void drawCells(BitSet[] grid, BitSet[] prevGrid) {
    for (var i = 1; i <= height; i++) {
      for (var j = 1; j <= width; j++) {
        if (grid[i].get(j) != prevGrid[i].get(j)) {
          setCellColor(grid[i].get(j));
          drawCell(j - 1, i - 1);
        }
      }
    }
  }

  static void reset() {
    gcGolLayer.clearRect(0, 0, 1280, 720);
    gcGrid.clearRect(0, 0, 1280, 720);
  }

  static void setGcGrid(GraphicsContext gcGrid) {
    ViewGOL.gcGrid = gcGrid;
  }

  static void setGcGolLayer(GraphicsContext gcGolLayer) {
    ViewGOL.gcGolLayer = gcGolLayer;
  }

  static void setDim(int dim) {
    ViewGOL.dim = dim;
  }

  static void setCellColor(boolean state) {
    color = state ? Color.web("#FF8F00") : Color.web("#566573");
  }

  static void setCellColor(String state) {
    color = switch (state) {
      case "ALIVE" -> Color.web("#FF8F00");
      case "DEAD"  -> Color.web("#566573");
      default      -> throw new IllegalArgumentException();
    };
  }

  public static void setWidth(int width) {
    ViewGOL.width = width;
  }

  public static void setHeight(int height) {
    ViewGOL.height = height;
  }

}
