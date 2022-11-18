package fr.chalodss.data;

import java.util.Arrays;
import java.util.List;
import fr.chalodss.beans.Player;

/**
 * @author Charles T.
 * 
 */
public final class Data {

  private static final List<Player> players = Arrays.asList(new Player("Charles", 1200), new Player("HAL", 3500));

  private Data() {

  }

  public static List<Player> getPlayers() {
    return players;
  }

}
