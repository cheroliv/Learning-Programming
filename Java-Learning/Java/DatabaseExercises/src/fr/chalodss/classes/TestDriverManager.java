package fr.chalodss.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Charles T.
 * 
 */
public final class TestDriverManager {

  private TestDriverManager() {

  }

  public static Connection getConnection(String file) throws IOException, SQLException {
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
    return DriverManager.getConnection(url, user, password);
  }

  public static ResultSet select(Statement stmt, String query) throws SQLException {
    return stmt.executeQuery(query);
  }

  public static int insert(Statement stmt, String query) throws SQLException {
    return stmt.executeUpdate("insert into " + query);
  }

  public static int delete(Statement stmt, String query) throws SQLException {
    return stmt.executeUpdate("delete from " + query);
  }

  public static int update(Statement stmt, String query) throws SQLException {
    return stmt.executeUpdate("update " + query);
  }

}
