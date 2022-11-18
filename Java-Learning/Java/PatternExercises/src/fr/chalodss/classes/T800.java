package fr.chalodss.classes;

import fr.chalodss.interfaces.IRobot;

/**
 * @author Charles T.
 * 
 */
public final class T800 implements IRobot {

  @Override
  public void attack() {
    System.out.println("T800 attacks.");
  }

}
