package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class Bear extends Mammal {

  private String name;

  public Bear() {
    System.out.println("Building a bear.");
  }

  public Bear(int mass, String family, String name) {
    super(mass, family);
    this.name = name;
    System.out.println("Building a bear with mass, family and name.");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
