package fr.chalodss.classes;

import static fr.chalodss.classes.Collisions.*;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * @author Charles T.
 *
 */
public final class Controller {

  @FXML
  private Pane            board;
  @FXML
  private Text            score;
  @FXML
  private Text            level;
  @FXML
  private CheckBox        aiMode;
  @FXML
  private Canvas          background;

  private GraphicsContext gcTetro;

  private LongProperty    timeInterval;

  private AnimationTimer  loop;

  private Game            game;


  public Controller() {

  }

  @FXML
  private void initialize() {
    GraphicsContext gcBg             = background.getGraphicsContext2D();
    Canvas          tetroLayerCanvas = new Canvas(350, 700);

    Renderer.setGcBg(gcBg);
    Renderer.renderGrid(10, 350, 700);
    gcTetro= tetroLayerCanvas.getGraphicsContext2D();
    Renderer.setGcTetro(gcTetro);
    board.getChildren().add(tetroLayerCanvas);
  }

  @FXML
  private void start() {
    init();
    game = new Game();
    game.end.addListener((obs, old, newv) -> score.setFill(Color.RED));

    level.textProperty().bind(game.level.asString());
    score.textProperty().bind(game.score.asString());
    timeInterval.bind(game.time);

    loop.start();
  }

  private void init() {
    timeInterval = new SimpleLongProperty(1_000_000_000L);

    background.setOnKeyPressed(this::getInputs);
    background.requestFocus();
    if (loop != null) {
      loop.stop();
    } else {
      loop = new AnimationTimer() {
        long lastTime;

        @Override
        public void handle(long now) {
          long time = now - lastTime;
          if (time >= timeInterval.get()) {
            game.update();
            game.endGame();
            lastTime = now;
          }
          Renderer.renderTetrominos(game.tetrominos);
        }
      };
    }
  }

  private void getInputs(KeyEvent e) {
    switch (e.getCode()) {
      case J:
        if (isNotLeftCollided(game.curTetro, game.board))
          game.curTetro.move(-1, 0);
        break;
      case K:
        if (isNotBottomCollided(game.curTetro, game.board))
          game.curTetro.move(0, 1);
        break;
      case L:
        if (isNotRightCollided(game.curTetro, game.board))
          game.curTetro.move(1, 0);
        break;
      case C:
        if (isNotTetroCollided(game.curTetro, game.tetrominos))
          game.curTetro.rotate();
        break;
      case SPACE:
        game.dropTetromino();
        break;
      default:
        break;
    }
  }

}
