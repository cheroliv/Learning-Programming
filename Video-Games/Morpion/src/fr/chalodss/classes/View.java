package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;
import java.util.stream.IntStream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * @author Charles T.
 *
 */
public final class View {

  static final Image     CROSS  = new Image("/cross.jpg", 240, 240, false, false);
  static final Image     CIRCLE = new Image("/circle.jpg", 240, 240, false, false);

  static GraphicsContext gc;

  private View() {

  }

  static void drawMove(Image img, int x, int y) {
    gc.drawImage(img, x * CASE_W + 5, y * CASE_W + 5);
  }

  static void drawMove(Image img, int num) {
    int x = num % 3;
    int y = num / 3;

    gc.drawImage(img, x * CASE_W + 5, y * CASE_W + 5);
  }

  static void drawBoard() {
    gc.setFill(Color.WHITE);
    gc.setLineWidth(1);
    gc.clearRect(0, 0, BOARD_W, BOARD_W);
    gc.setFill(Color.BLACK);
    IntStream.range(1, 3).forEach(i -> gc.strokeLine(i * CASE_W, 0, i * CASE_W, BOARD_W));
    IntStream.range(1, 3).forEach(j -> gc.strokeLine(0, j * CASE_W, BOARD_W, j * CASE_W));
  }

  static void reset() {
    gc.clearRect(0, 0, BOARD_W, BOARD_W);
  }

  static void setGc(GraphicsContext gc) {
    View.gc = gc;
  }

}
