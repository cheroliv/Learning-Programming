package fr.chalodss.app;

import fr.chalodss.classes.HandleEntity;
import fr.chalodss.classes.Player;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    var player = new Player(12, "Tryphon", "Tournesol", 2500);
    var hem    = new HandleEntity("players");

    System.out.println(hem.listAll("Player"));
    hem.insert(player);
    System.out.println(hem.listAll("Player"));
    hem.close();
  }

}
