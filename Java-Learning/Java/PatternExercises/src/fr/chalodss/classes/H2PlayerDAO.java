package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import fr.chalodss.interfaces.IDAOPlayer;

public final class H2PlayerDAO implements IDAOPlayer {

  private Connection   con     = null;
  private List<Player> players = new ArrayList<>();

  @Override
  public void setUp() throws SQLException {

  }

  @Override
  public void connect(String file) throws IOException, SQLException {
    var url      = "";
    var user     = "";
    var password = "";

    try (var input = new FileInputStream(file)) {
      var props = new Properties();
      props.load(input);
      url      = props.getProperty("H2_DB_URL");
      user     = props.getProperty("H2_DB_USER");
      password = props.getProperty("H2_DB_PASSWORD");
    }
    con = DriverManager.getConnection(url, user, password);
  }

  @Override
  public void close() throws SQLException {
    con.close();
  }

  @Override
  public long insert(Player player) throws SQLException {
    try (var ps = con.prepareStatement("insert into player values (?, ?, ?, ?, ?)")) {
      ps.setInt(1, player.getId());
      ps.setString(2, player.getFirstName());
      ps.setString(3, player.getLastName());
      ps.setInt(4, player.getElo());
      return ps.executeUpdate();
    }
  }

  @Override
  public boolean updatePlayer(Player player) {
    return false;
  }

  @Override
  public boolean deletePlayer(Player player) {
    return false;
  }

  @Override
  public List<Player> findPlayerByProperty(EPlayerSearchType type, Object value) {
    return players;
  }

  @Override
  public List<Player> findAllPlayer() throws SQLException {
    try (var stmt = con.createStatement(); var rs = stmt.executeQuery("select * from player")) {
      while (rs.next()) {
       players.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
      }
    }
    return players;
  }

}
