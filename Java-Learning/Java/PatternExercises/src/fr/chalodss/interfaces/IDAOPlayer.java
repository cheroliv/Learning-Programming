package fr.chalodss.interfaces;

/**
 * @author Charles T.
 * 
 */
import java.sql.SQLException;
import java.util.List;
import fr.chalodss.classes.EPlayerSearchType;
import fr.chalodss.classes.Player;

public interface IDAOPlayer extends IDAO {

  public long insert(Player player) throws SQLException;

  public boolean updatePlayer(Player player);

  public boolean deletePlayer(Player player);

  public List<Player> findPlayerByProperty(EPlayerSearchType type, Object value);

  public List<Player> findAllPlayer() throws SQLException;

}
