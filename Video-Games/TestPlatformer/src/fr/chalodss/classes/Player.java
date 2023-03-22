package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.PLAYER_IMG;

import javafx.scene.image.ImageView;

public final class Player extends ImageView {

  int     velX;
  int     velY;

  public Player(double x, double y) {
    setX(x);
    setY(y);
    setImage(PLAYER_IMG);
  }

}
