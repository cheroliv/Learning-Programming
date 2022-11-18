package fr.chalodss.app;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import fr.chalodss.classes.ChessTournament;
import fr.chalodss.classes.EThePasser;
import fr.chalodss.classes.H2PlayerDAO;
import fr.chalodss.classes.Human;
import fr.chalodss.classes.Person;
import fr.chalodss.classes.Player;
import fr.chalodss.classes.RobotFactory;
import fr.chalodss.classes.T800;
import fr.chalodss.classes.ThePasser;
import fr.chalodss.interfaces.IDAOPlayer;
import fr.chalodss.interfaces.IRobot;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void testSingleton()
      throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    System.out.println("---------- SINGLETON PATTERN ----------");
    var p1 = ThePasser.getInstance();
    p1.incCount();
    var p2 = ThePasser.getInstance();
    p2.incCount();

    System.out.println((p1 == p2) ? "p1 = p2" : "p1 != p2");
    System.out.println(p1.getCount() + " " + p2.getCount());

    Constructor<ThePasser> c = ThePasser.class.getDeclaredConstructor();
    c.setAccessible(true);
    ThePasser p3 = c.newInstance();
    p3.incCount();

    System.out.println(((p1 == p2) && (p2 == p3)) ? "p1 = p2 = p3" : "Not the same object");
    System.out.println(p1.getCount() + " " + p2.getCount() + " " + p3.getCount());

    EThePasser ep1 = EThePasser.THE_PASSER;
    ep1.incCount();
    // Constructor<EThePasser> e = EThePasser.class.getConstructor();
    // e.setAccessible(true);
    // EThePasser ep2 = e.newInstance();
    EThePasser ep2 = EThePasser.THE_PASSER;
    ep2.incCount();

    System.out.println(ep1 == ep2);
    System.out.println(ep1.getCount() + " " + ep2.getCount());
  }

  public static void testBuilder() {
    System.out.println("----------- BUILDER PATTERN -----------");
    System.out.println("Create a new Person");
    var p1 = new Person.PersonBuilder("goku", LocalDate.of(1920, 1, 1)).build();
    var p2 = new Person.PersonBuilder("vegeta", LocalDate.of(1910, 1, 1)).setPhone("0123").build();
    var p3 = new Person.PersonBuilder("broly", LocalDate.of(1930, 1, 1)).setPhone("0123").setMail("aaa@ooo.com").build();
    System.out.println(p1);
    System.out.println(p2);
    System.out.println(p3);
  }

  public static void testImmutable() {
    System.out.println("---------- IMMUTABLE PATTERN ----------");
    var ihuman = new Human("charles", Arrays.asList("a", "b", "c"));
    System.out.println(ihuman.getFavoriteInterest(1));
  }

  public static void testFactory() {
    System.out.println("----------- FACTORY PATTERN -----------");
    IRobot robot = RobotFactory.buildRobotFactory(T800.class);
    robot.attack();
  }

  public static void testDAO() throws IOException, SQLException {
    System.out.println("------------- DAO PATTERN -------------");
    IDAOPlayer playerDAO = new H2PlayerDAO();
    playerDAO.connect("resources/db.properties");
    var tournament = new ChessTournament(playerDAO);
    tournament.addPlayer(new Player(11, "Henry", "Dupond", 1200));
    System.out.println(tournament.getPlayers());
  }

  public static void main(String[] args) {
    try {
      testSingleton();
      testBuilder();
      testImmutable();
      testFactory();
      testDAO();
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | IOException
        | SQLException e) {
      e.printStackTrace();
    }
  }

}
