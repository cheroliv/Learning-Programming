package fr.chalodss.app;

import fr.chalodss.classes.Computer;
import fr.chalodss.classes.EScanner;
import fr.chalodss.classes.Game;
import fr.chalodss.classes.Human;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    char resp;

    do {
      System.out.print("\nGive a name for human player : ");
      var s1    = EScanner.get();
      var human = new Human(s1);
      System.out.print("Give a name for computer     : ");
      var s2       = EScanner.get();
      var computer = new Computer(s2);
      System.out.print("Give the number of packets   : ");
      var nbPackets = Integer.parseInt(EScanner.get());
      var game      = new Game(nbPackets, human, computer);
      game.run();
      System.out.print("\nAnother game? y/n ");
      resp = EScanner.get().charAt(0);
    } while (resp == 'y');
  }

}
