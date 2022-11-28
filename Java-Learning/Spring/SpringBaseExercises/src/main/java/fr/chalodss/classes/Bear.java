package fr.chalodss.classes;

public final class Bear {

  private String name;
  private int    mass;

  public Bear() {

  }

  public void eat(Food food) {
    System.out.println("I am eating " + food + ".");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMass() {
    return mass;
  }

  public void setMass(int mass) {
    this.mass = mass;
  }

}
