package fr.chalodss.classes;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Charles T.
 * 
 */

@XmlRootElement
public final class Person {

  private int    i;
  private String name;

  public Person() {

  }

  public Person(int i, String name) {
    super();
    this.i    = i;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person [i=" + i + ", name=" + name + "]";
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
