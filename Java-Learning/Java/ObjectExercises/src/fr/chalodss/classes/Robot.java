package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public abstract class Robot {

  protected final int serialNumber;

  protected Robot(int serialNumber) {
    this.serialNumber = serialNumber;
  }

  public abstract void attack();

}
