package fr.chalodss.classes;

import fr.chalodss.interfaces.IMove;

/**
 * @author Charles T.
 * 
 */
public class Aircraft implements IMove {

  private int nbWings;

  public Aircraft(int nbWings) {
    this.nbWings = nbWings;
  }

  @Override
  public void move() {
    System.out.println("I move in the air.");
  }

  public int getNbWings() {
    return nbWings;
  }

  public void setNbWings(int nbWings) {
    this.nbWings = nbWings;
  }

}
