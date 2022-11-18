package fr.chalodss.classes;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Charles T.
 *
 */
public final class Player extends Rectangle {

  double vel;

  public Player(double x, double y, double w, double h) {
    super(x, y, w, h);
    setFill(Color.WHITE);
    setOnKeyPressed(this::onKeyPressed);
    setOnKeyReleased(this::onKeyReleased);
  }

  void move(int vel) {
    this.vel = vel;
    setY(getY() + vel);
  }

  void onKeyPressed(KeyEvent e) {
    switch (e.getCode()) {
      case J:
        if (getY() >= 10)
          move(-10);
        break;
      case N:
        if (getY() <= 600) {
          move(10);
        }
        break;
      default:
        break;
    }
  }

  void onKeyReleased(KeyEvent e) {
    vel = 0;
  }

}
