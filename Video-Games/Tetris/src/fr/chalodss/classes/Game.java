package fr.chalodss.classes;

import static fr.chalodss.classes.Collisions.isNotBottomCollided;
import static fr.chalodss.utils.Constants.TETROMINOS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 * @author Charles T.
 *
 */
public final class Game {

  final BooleanProperty end        = new SimpleBooleanProperty(false);
  final IntegerProperty level      = new SimpleIntegerProperty(1);
  final IntegerProperty score      = new SimpleIntegerProperty(0);
  final LongProperty    time       = new SimpleLongProperty(1_000_000_000L);

  private final Random  rand       = new Random();
  private int           scoreLimit = 400;
  final int[][]         board      = new int[24][10];
  final List<Tetromino> tetrominos = new ArrayList<>();
  
  Tetromino             curTetro   = new Tetromino(TETROMINOS.get(rand.nextInt(7)));


  public Game() {
    tetrominos.add(curTetro);
  }

  void update() {
    if (isNotBottomCollided(curTetro, board)) {
      curTetro.squares.forEach(s -> s.y++);
    } else {
      curTetro.squares.forEach(s -> board[s.y][s.x] = 1);
      int nbRows = updateRows();
      updateScore(nbRows);
      updateLevel();
      curTetro = new Tetromino(TETROMINOS.get(rand.nextInt(7)));
      tetrominos.add(curTetro);
    }
  }

  void updateLevel() {
    if (score.get() >= scoreLimit && level.get() <= 10 && time.get() >= 200_000_000L) {
      level.set(level.get() + 1);
      time.set(time.subtract(100_000_000L).get());
      scoreLimit += 400;
    }
  }

  void updateScore(int nbLines) {
    score.set(score.get() + 100 * nbLines);
  }

  int updateRows() {
    var rows = 0;
    var i    = 23;

    while (i > 0) {
      if (Arrays.stream(board[i]).filter(e -> e == 1).count() == 10) {
        rows++;
        resetGrid();
        var lim = i;
        tetrominos.forEach(t -> t.squares.removeIf(s -> s.y == lim));
        tetrominos.removeIf(t -> t.squares.isEmpty());
        tetrominos.forEach(t -> t.squares.forEach(s -> s.y += (s.y < lim) ? 1 : 0));
        tetrominos.forEach(t -> t.squares.forEach(s -> board[s.y][s.x] = 1));
      } else {
        i--;
      }
    }
    return rows;
  }

  void dropTetromino() {
    while (isNotBottomCollided(curTetro, board)) {
      curTetro.move(0, 1);
    }
  }

  void endGame() {
    end.setValue(Arrays.stream(board[3]).anyMatch(e -> e == 1));
  }

  void resetGrid() {
    for (int[] line : board) {
      Arrays.fill(line, 0);
    }
  }

}
