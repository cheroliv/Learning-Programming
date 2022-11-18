package fr.chalodss.classes;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public final class Collisions {

  private Collisions() {

  }

  static boolean isNotBottomCollided(Tetromino tetro, int[][] board) {
    return tetro.squares.stream().allMatch(s -> s.y < 23 && board[s.y + 1][s.x] == 0);
  }

  static boolean isNotLeftCollided(Tetromino tetro, int[][] board) {
    IntPredicate p = x -> x > 0;

    return tetro.squares.stream().allMatch(s -> p.test(s.x) && board[s.y][s.x - 1] == 0);
  }

  static boolean isNotRightCollided(Tetromino tetro, int[][] board) {
    IntPredicate p = x -> x < 9;

    return tetro.squares.stream().allMatch(s -> p.test(s.x) && board[s.y][s.x + 1] == 0);
  }

  static boolean isNotTetroCollided(Tetromino tetro, List<Tetromino> tetrominos) {
    var list = tetrominos.stream().filter(t -> t != tetro).collect(Collectors.toList());
    var tmp  = new Tetromino(tetro);

    tmp.rotate();
    for (Square s : tmp.squares) {
      if (s.x < 0 || s.x > 9 || s.y > 23)
        return false;
      for (Tetromino t : list) {
        for (Square c : t.squares) {
          if (s.x == c.x && s.y == c.y) {
            return false;
          }
        }
      }
    }
    return true;
  }

}
