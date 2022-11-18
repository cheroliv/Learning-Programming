package fr.chalodss.classes;

import static fr.chalodss.utils.Images.BEAM_PLAYER;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class Player extends ImageView {

  EState  state;
  int     velX;
  boolean canShoot;
  Entity  beam;

  public Player(double x, double y, Image img) {
    setX(x);
    setY(y);
    setImage(img);
    beam     = new Entity(-20, 0, BEAM_PLAYER);
    canShoot = true;
    setOnKeyPressed(this::onKeyPressed);
    setOnKeyReleased(this::onKeyReleased);
  }

  void move(int velX) {
    this.velX = velX;
    setX(getX() + velX);
  }

  void shoot() {
    beam.setX(getX() + 23);
    beam.setY(getY() - 10);
    canShoot = false;
  }

  void onKeyPressed(KeyEvent e) {
    switch (e.getCode()) {
      case J:
        if (getX() >= 7) {
          move(-7);
        }
        break;
      case L:
        if (getX() <= 950) {
          move(7);
        }
        break;
      default:
        break;
    }
    if (e.getCode() == KeyCode.C && canShoot) {
      shoot();
    }
  }

  void onKeyReleased(KeyEvent e) {
    velX = 0;
  }

}
