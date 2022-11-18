package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
public final class ThePasser {

  private static ThePasser thePasser = null;
  private String           name;
  private int              count;

  private ThePasser() {

  }

  public static synchronized ThePasser getInstance() {
    if (thePasser == null) {
      thePasser = new ThePasser();
    }
    return thePasser;
  }

  public void incCount() {
    count++;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

}
