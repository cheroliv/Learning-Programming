package fr.chalodss.classes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;

/**
 * @author Charles T.
 *
 */
public final class Tetromino {

  private static final int SIZE = 3;

  final Color              color;
  final List<Square>       squares;


  public Tetromino(Color color, Square... squares) {
    this.color   = color;
    this.squares = Arrays.asList(squares);
  }

  public Tetromino(Tetromino t) {
    this.color   = t.color;
    this.squares = t.squares.stream().map(Square::new).collect(Collectors.toList());
  }

  void move(int dx, int dy) {
    squares.forEach(s -> {
      s.x += dx;
      s.y += dy;
    });
  }

  void rotate() {
    var origin = squares.get(0);

    for (var i = 1; i <= SIZE; i++) {
      var s = squares.get(i);
      var x = s.x;
      s.x = origin.x + origin.y - s.y;
      s.y = origin.y - origin.x + x;
    }
  }

}
