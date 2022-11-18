package fr.chalodss.classes;

import static fr.chalodss.classes.EState.*;
import static fr.chalodss.utils.Constants.*;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Charles T.
 *
 */
public final class Board {

  final Cell[]  grid;
  private int[] rows;
  private int[] cols;
  private int   lDiag;
  private int   rDiag;

  public Board() {
    grid  = new Cell[NB_CELLS];
    rows  = new int[NB_CASES];
    cols  = new int[NB_CASES];
    lDiag = 0;
    rDiag = 0;
    Stream.iterate(0, n -> n + 1).limit(NB_CELLS).forEach(i -> grid[i] = new Cell(i, E));
  }

  void update(int num, EState state) {
    int c = num % 3;
    int r = num / 3;

    rows[r] += state.value;
    cols[c] += state.value;
    lDiag   += (r == c) ? state.value : 0;
    rDiag   += (r + c == NB_CASES - 1) ? state.value : 0;
  }

  int hasWon() {
    int winner = 0;

    if (Arrays.stream(rows).filter(v -> v == 3).count() == 1
        || Arrays.stream(cols).filter(v -> v == 3).count() == 1
        || lDiag == 3
        || rDiag == 3) {
      winner = 1;
    } else if (Arrays.stream(rows).filter(v -> v == -3).count() == 1
        || Arrays.stream(cols).filter(v -> v == -3).count() == 1
        || lDiag == -3
        || rDiag == -3) {
      winner = -1;
    }
    return winner;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    var k  = 0;

    for (var i = 0; i < 3; i++) {
      sb.append("|");
      for (var j = 0; j < 3; j++) {
        sb.append(grid[k++].state + "|");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

}
