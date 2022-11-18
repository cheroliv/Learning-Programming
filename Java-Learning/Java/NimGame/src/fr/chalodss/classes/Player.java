package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
public abstract class Player {

  protected String name;

  protected Player(String name) {
    this.name = name;
  }

  abstract int getMatches(int[] packets);

}
