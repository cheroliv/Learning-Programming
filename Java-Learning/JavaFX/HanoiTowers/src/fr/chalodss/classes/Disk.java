package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Charles T.
 *
 */
public final class Disk extends Rectangle {

  double[] xPos;

  public Disk(double x, double y, double w, double h) {
    super(x, y, w, h);
    setFill(Color.web("#FFFFFF"));
    setStrokeWidth(2);
    setStroke(Color.WHITE);
    setxPos(w);
  }

  void setxPos(double w) {
    xPos    = new double[3];
    xPos[0] = T1X - w / 2;
    xPos[1] = T2X - w / 2;
    xPos[2] = T3X - w / 2;
  }

  void move(double x, double y) {
    setX(x);
    setY(y);
  }

}
