package fr.chalodss.app;

import java.util.Scanner;
import fr.chalodss.classes.Game;
import fr.chalodss.classes.Player;

/**
 * @author Charles T.
 *
 */
public class Main {

  public static void main(String[] args) {
    var  sc   = new Scanner(System.in);
    char resp = 0;

    do {
      System.out.println("Give a name for player 1: ");
      var s1 = sc.nextLine();
      var p1 = new Player(s1);
      System.out.println("Give a name for player 2: ");
      var s2   = sc.nextLine();
      var p2   = new Player(s2);
      var game = new Game(p1, p2);
      game.run();
      System.out.println("Another game? y/n ");
      resp = sc.nextLine().charAt(0);
    } while (resp == 'y');
    sc.close();
  }

}
