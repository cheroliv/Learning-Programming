package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;

/**
 * @author Charles T.
 * 
 */
public final class Game extends AnimationTimer {

  final IntegerProperty score = new SimpleIntegerProperty(0);

  final Player          player;
  final Computer        cpu;
  final Circle          ball;

  double                a     = atan2(VY, VX);
  double                mag   = sqrt(MAG);
  double                dX    = mag * cos(a);
  double                dY    = mag * sin(a);


  public Game(Player player, Computer cpu, Circle ball) {
    this.player = player;
    this.cpu    = cpu;
    this.ball   = ball;
  }

  @Override
  public void handle(long now) {
    handlePlayer();
    update();
    endGame();
  }

  void endGame() {
    if (ball.getCenterX() < 15) {
      stop();
    }
  }

  void handlePlayer() {
    if (player.getY() >= 10 && player.getY() <= 600) {
      player.setY(player.getY() + player.vel);
    }
  }

  void reset() {
    stop();
    score.set(0);
    ball.setCenterX(450);
    ball.setCenterY(350);
    player.setY(150);
    cpu.setY(450);
    a   = atan2(VY, VX);
    mag = sqrt(MAG);
    dX  = mag * cos(a);
    dY  = mag * sin(a);
  }

  void update() {
    ball.setCenterX(ball.getCenterX() + dX);
    ball.setCenterY(ball.getCenterY() + dY);
    cpu.setY(ball.getCenterY() - 75);

    if (player.getBoundsInParent().intersects(ball.getBoundsInParent())) {
      mag *= (mag < SPEED) ? ACC : 1;
      a    = C * abs((player.getY() + 75 - ball.getCenterY() - 15) / 75);
      dX   = abs(mag * cos(a));
      dY   = abs(mag * sin(a));
      dY   = (ball.getCenterY() <= player.getY() + 75) ? -dY : dY;
      score.set(score.get() + 1);
    } else if (cpu.getBoundsInParent().intersects(ball.getBoundsInParent())) {
      dX = -dX;
    } else if (ball.getCenterY() > BOTTOM) {
      dY = -dY;
      ball.setCenterY(719);
    } else if (ball.getCenterY() < TOP) {
      dY = -dY;
      ball.setCenterY(16);
    }
  }

}
