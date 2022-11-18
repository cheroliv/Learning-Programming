package fr.chalodss.classes;

import static fr.chalodss.classes.EPlayer.*;
import static fr.chalodss.classes.EState.*;
import static fr.chalodss.utils.Constants.*;

import java.util.Arrays;
import java.util.Random;
import fr.chalodss.interfaces.IPlay;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;

/**
 * @author Charles T.
 *
 */
public final class Game extends Task<Void> {

  final StringProperty  winner  = new SimpleStringProperty("-------");
  final IntegerProperty pos     = new SimpleIntegerProperty(0);

  private final Random  rand    = new Random();
  private final IPlay   computer;

  private int           nbMoves = NB_MOVES;
  final Board           board   = new Board();
  Cell                  cell;
  EPlayer               move;
  boolean               end;

  public Game(EPlayer firstPlayer, int mode) {
    computer = mode == 0 ? this::randomGame : this::perfectGame;
    cell     = new Cell(-1, E);
    end      = false;
    move     = firstPlayer;
  }

  @Override
  protected Void call() throws Exception {
    do {
      switch (move) {
        case HUMAN -> play();
        case COMPUTER -> computer.play();
        default -> throw new IllegalArgumentException();
      }
      nbMoves--;
    } while (!isEndGame());
    return null;
  }

  private boolean isEndGame() {
    int w = board.hasWon();

    end = (w != 0) || (nbMoves == 0);
    if (end) {
      winner.set(w == 1 ? "HUMAN" : (w == -1) ? "COMPUTER" : "DRAW");
    }
    return end;
  }

  private void play() {
    while (move == HUMAN) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private void randomGame() {
    var validCells = Arrays.stream(board.grid).filter(c -> c.state == E).toList();

    cell       = validCells.get(rand.nextInt(validCells.size()));
    cell.state = O;
    pos.set(cell.num);
    board.update(cell.num, O);
    move = HUMAN;
  }

  private void perfectGame() {

  }

}
