package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;
import static fr.chalodss.classes.EPlayer.*;
import static fr.chalodss.classes.EState.*;
import static fr.chalodss.classes.View.*;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * @author Charles T.
 *
 */
public final class Controller {

  @FXML
  private Canvas           board;
  @FXML
  private CheckBox         computer;
  @FXML
  private CheckBox         human;
  @FXML
  private ComboBox<String> level;
  @FXML
  private Label            winner;

  private GraphicsContext  gc;

  private Game             game;

  public Controller() {

  }

  @FXML
  private void initialize() {
    gc = board.getGraphicsContext2D();
    level.setItems(FXCollections.observableList(Arrays.asList(RANDOM, PERFECT)));
    View.setGc(gc);
  }

  @FXML
  private void start() {
    if (game != null) {
      reset();
    }
    var firstPlayer = human.isSelected() ? HUMAN : COMPUTER;
    var mode        = level.getSelectionModel().getSelectedIndex();

    game = new Game(firstPlayer, mode);
    game.pos.addListener((obs, old, newv) -> drawMove(CIRCLE, game.cell.num));
    game.winner.addListener((obs, old, newv) -> updateWinner());
    board.setOnMouseClicked(this::getInputs);
    drawBoard();
    new Thread(game).start();

  }

  private void reset() {
    game.end  = true;
    game.move = END;
    game.winner.set("-------");
    game.cancel();
    View.setGc(gc);
  }

  private void getInputs(MouseEvent e) {
    if (!game.end) {
      int i = (e.getX() < CASE_W) ? 0 : (e.getX() < 500) ? 1 : 2;
      int j = (e.getY() < CASE_W) ? 0 : (e.getY() < 500) ? 1 : 2;
      drawMove(CROSS, i, j);
      game.move                        = COMPUTER;
      game.board.grid[j * 3 + i].state = X;
      game.board.update(j * 3 + i, X);
    }
  }

  private void updateWinner() {
    Platform.runLater(() -> winner.setText(game.winner.get()));
  }

}
