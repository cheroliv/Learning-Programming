package fr.chalodss.interfaces;

/**
 * @author Charles T.
 * 
 */
import java.io.IOException;
import java.sql.SQLException;

public interface IDAO {

  public void setUp() throws SQLException;

  public void connect(String file) throws IOException, SQLException;

  public void close() throws SQLException;

}
