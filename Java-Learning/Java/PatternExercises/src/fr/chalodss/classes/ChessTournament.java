package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
import java.sql.SQLException;
import java.util.List;
import fr.chalodss.interfaces.IDAOPlayer;

public final class ChessTournament {

  private IDAOPlayer playerDAO;

  public ChessTournament(IDAOPlayer playerDAO) {
    this.playerDAO = playerDAO;
  }

  public void addPlayer(Player player) throws SQLException {
    playerDAO.insert(player);
  }

  public List<Player> getPlayers() throws SQLException {
    return playerDAO.findAllPlayer();
  }

}
