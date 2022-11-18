package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class T1000 extends Robot {

  public T1000(int serialNumber) {
    super(serialNumber);
  }

  @Override
  public void attack() {
    System.out.println("I attack by transforming my hands into knifes.");
  }

}
