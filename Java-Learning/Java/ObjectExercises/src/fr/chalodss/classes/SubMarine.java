package fr.chalodss.classes;

import fr.chalodss.interfaces.IMove;

/**
 * @author Charles T.
 * 
 */
public class SubMarine implements IMove {

  private int nbPropellers;

  public SubMarine(int nbPropellers) {
    this.nbPropellers = nbPropellers;
  }

  @Override
  public void move() {
    System.out.println("I move underwater.");
  }

  public int getNbPropellers() {
    return nbPropellers;
  }

  public void setNbPropellers(int nbPropellers) {
    this.nbPropellers = nbPropellers;
  }

}
