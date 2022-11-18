package fr.chalodss.classes;

import static fr.chalodss.classes.EState.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Charles T.
 *
 */
public final class Entity extends ImageView {

  EState state;

  public Entity(double x, double y, Image img) {
    this.setX(x);
    this.setY(y);
    this.state = ALIVE;
    this.setImage(img);
  }

  void moveX(double velX) {
    setX(getX() + velX);
  }

  void moveY(double velY) {
    setY(getY() + velY);
  }

  boolean isDead() {
    return state == DEAD;
  }

}
