package fr.chalodss.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.chalodss.models.Player;

/**
 * @author Charles T.
 * 
 */
public final class PlayerService {

  private PlayerService() {

  }

  public static List<Player> getPlayers(Connection con) throws SQLException {
    var list = new ArrayList<Player>();

    try (var stmt = con.createStatement(); var rs = stmt.executeQuery("select * from player")) {
      while (rs.next()) {
        list.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
      }
    }
    return list;
  }

}
