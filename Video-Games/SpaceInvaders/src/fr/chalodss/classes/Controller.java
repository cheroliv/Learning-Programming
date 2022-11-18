package fr.chalodss.classes;

import static fr.chalodss.classes.Init.initInvaders;
import static fr.chalodss.classes.Init.initWalls;
import static fr.chalodss.utils.Images.PLAYER;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Charles T.
 *
 */
public final class Controller {
  @FXML
  private Pane           board;
  @FXML
  private Text           score;

  BooleanProperty        end;

  private Player         player;
  private Game           game;
  private AnimationTimer loop;
  private Renderer       renderer;

  public Controller() {

  }

  @FXML
  private void init() {
    var invaders = initInvaders(60, 60);
    var walls    = initWalls(100, 500);

    end      = new SimpleBooleanProperty(false);
    player   = new Player(475, 660, PLAYER);
    game     = new Game(player, invaders, walls);
    renderer = new Renderer();

    board.getChildren().add(game.player);
    board.getChildren().add(game.player.beam);
    board.getChildren().addAll(game.invaders);
    board.getChildren().addAll(game.walls);

    score.textProperty().bind(Bindings.convert(game.score));
    end.bind(game.end);
  }

  @FXML
  private void start() {
    loop = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (!end.get()) {
          game.handlePlayer();
          game.updateInvaders();
          game.updateBeams();
          game.updateCollisions();
          renderer.render(board, game);
        } else {
          stop();
        }
      }
    };
    player.requestFocus();
    loop.start();
  }

  @FXML
  private void reset() {
    loop.stop();
    board.getChildren().clear();
  }

}
