package fr.chalodss.app;

import fr.chalodss.classes.EScanner;
import fr.chalodss.classes.Game;

/**
 * @author Charles T.
 *
 */
public class Main {

  public static void main(String[] args) {
    char resp;

    do {
      new Game(args[0]).run();
      System.out.println("Another game? y / n");
      resp = EScanner.get().charAt(0);
    } while (resp == 'y');
  }

}
