package fr.chalodss.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

/**
 * @author Charles T.
 * 
 */
public final class TestDataSource {

  private TestDataSource() {

  }

  public static DataSource getDataSource(String file) throws IOException {
    var dts = new JdbcDataSource();

    try (var input = new FileInputStream(file)) {
      var props = new Properties();
      props.load(input);
      dts.setUrl(props.getProperty("H2_DB_URL"));
      dts.setUser(props.getProperty("H2_DB_USER"));
      dts.setPassword(props.getProperty("H2_DB_PASSWORD"));
    }
    return dts;
  }

  public static void select(Connection con, String query, String criteria, int... col) throws SQLException {
    try (var ps = con.prepareStatement(query)) {
      ps.setString(1, criteria);
      try (var rs = ps.executeQuery()) {
        while (rs.next()) {
          for (int n : col) {
            System.out.printf("%-10s", rs.getString(n));
          }
          System.out.println();
        }
      }
    }
  }

}
