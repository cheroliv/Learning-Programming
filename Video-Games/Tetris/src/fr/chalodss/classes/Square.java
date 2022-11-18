package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class Square {

  int x;
  int y;

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Square(Square s) {
    this.x = s.x;
    this.y = s.y;
  }

}
