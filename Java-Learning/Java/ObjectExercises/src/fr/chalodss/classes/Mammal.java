package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public class Mammal extends Animal {

  protected String family;

  public Mammal() {
    System.out.println("Building a mammal.");
  }

  public Mammal(int mass, String family) {
    super(mass);
    this.family = family;
    System.out.println("Building a mammal with mass and family.");
  }

}
