package fr.chalodss.app;

import static fr.chalodss.utils.Constants.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.chalodss.classes.TestDataSource;
import fr.chalodss.classes.TestDriverManager;

/**
 * @author Charles T.
 * 
 */
public class Main {

  private static void displayResult(ResultSet rs, int... col) throws SQLException {
    while (rs.next()) {
      for (var n : col) {
        System.out.printf("%-10s", rs.getString(n));
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws IOException {
    try {
      ResultSet rs   = null;
      var       con  = TestDriverManager.getConnection(DB_PROPERTIES);
      var       stmt = con.createStatement();

      rs = TestDriverManager.select(stmt, SELECT_ALL);
      displayResult(rs, 1, 2, 3, 4);
      System.out.println();
      TestDriverManager.insert(stmt, "player values(11, 'Henry', 'dupond', 1200)");
      rs = TestDriverManager.select(stmt, SELECT_ALL);
      displayResult(rs, 1, 2, 3, 4);
      System.out.println();
      TestDriverManager.update(stmt, "player set firstName = 'Louis' where lastName = 'dupond'");
      rs = TestDriverManager.select(stmt, SELECT_ALL);
      displayResult(rs, 1, 2, 3, 4);
      System.out.println();
      TestDriverManager.delete(stmt, "player where id = 11");
      rs = TestDriverManager.select(stmt, SELECT_ALL);
      displayResult(rs, 1, 2, 3, 4);
      con.close();
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
    System.out.println("----------------------------------");
    try {
      var dts = TestDataSource.getDataSource(DB_PROPERTIES);
      var con = dts.getConnection();

      TestDataSource.select(con, "select * from player where elo < ?", "2000", 2, 3, 4);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
