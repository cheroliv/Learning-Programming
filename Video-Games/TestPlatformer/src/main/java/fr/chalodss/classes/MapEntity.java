package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import javafx.scene.image.ImageView;

public final class MapEntity extends ImageView {

  final EMapType type;

  public MapEntity(double x, double y, EMapType type) {
    setX(x);
    setY(y);
    this.type = type;
    setImage(switch (type) {
      case GRASS        -> GRASS_IMG;
      case SKY          -> SKY_IMG;
      case BLUEBALL     -> BLUE_BALL_IMG;
      case REDBALL      -> RED_BALL_IMG;
      case YELLOWSQUARE -> YELLOW_SQUARE_IMG;
      default           -> EMPTY_IMG;
    });
  }

}
