package fr.chalodss.classes;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * @author Charles T.
 *
 */
public final class Controller {

  @FXML
  private Pane   board;
  @FXML
  private Circle ball;
  @FXML
  private Label  score;

  private Player player;
  private Game   game;


  public Controller() {

  }

  @FXML
  private void initialize() {
    Computer cpu = new Computer(1169, 450, 30, 150);

    player = new Player(1, 150, 30, 150);
    game   = new Game(player, cpu, ball);

    score.textProperty().bind(Bindings.convert(game.score));
    board.getChildren().add(player);
    board.getChildren().add(cpu);
  }

  @FXML
  private void run() {
    player.requestFocus();
    game.start();
  }

  @FXML
  private void reset() {
    game.reset();
  }

}
