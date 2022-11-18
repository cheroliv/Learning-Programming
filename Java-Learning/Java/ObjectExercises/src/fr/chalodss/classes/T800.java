package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class T800 extends Robot {

  public T800(int serialNumber) {
    super(serialNumber);
  }

  @Override
  public void attack() {
    System.out.println("I attack with my assault rifle.");
  }

}
