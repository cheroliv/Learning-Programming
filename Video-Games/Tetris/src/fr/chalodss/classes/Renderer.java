package fr.chalodss.classes;

import java.util.List;
import java.util.stream.IntStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Charles T.
 *
 */
public final class Renderer {

  public static final double TILE = 35.0;

  static GraphicsContext     gcBg;
  static GraphicsContext     gcTetro;

  private Renderer() {

  }

  static void renderGrid(int dim, double w, double h) {
    gcBg.setFill(Color.BLACK);
    gcBg.fillRect(0, 0, 350, 700);
    gcBg.setStroke(Color.WHITE);
    IntStream.range(1, dim).forEach(i -> gcBg.strokeLine(i * w / dim, 0, i * w / dim, h));
    IntStream.range(1, dim * 2).forEach(j -> gcBg.strokeLine(0, j * 0.5 * h / dim, w, j * 0.5 * h / dim));
  }

  static void renderTetrominos(List<Tetromino> tetrominos) {
    gcTetro.clearRect(0, 0, 350, 700);
    tetrominos.forEach(t -> {
      gcTetro.setFill(t.color);
      t.squares.forEach(s -> gcTetro.fillRect(s.x * TILE + 1, (s.y - 4) * TILE + 1, TILE - 2, TILE - 2));
    });
  }

  static void setGcBg(GraphicsContext gcBg) {
    Renderer.gcBg = gcBg;
  }

  public static void setGcTetro(GraphicsContext gcTetro) {
    Renderer.gcTetro = gcTetro;
  }

}
