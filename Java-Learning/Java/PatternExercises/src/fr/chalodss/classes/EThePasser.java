package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
public enum EThePasser {

  THE_PASSER;

  private String name;
  private int    count;

  private EThePasser() {

  }

  public void incCount() {
    count++;
  }

  public String getName() {
    return name;
  }

  public int getCount() {
    return count;
  }

}
