package fr.chalodss.classes;

import fr.chalodss.interfaces.IRobot;

/**
 * @author Charles T.
 * 
 */
public final class RobotFactory {

  private RobotFactory() {

  }

  public static IRobot buildRobotFactory(Class<?> robot) {
    return switch (robot.getSimpleName()) {
      case "T800" -> new T800();
      case "T1000" -> new T1000();
      default -> throw new UnsupportedOperationException("Invalid type of robot!");
    };
  }

  public static IRobot buildRobotFactory(ERobot robot) {
    return robot.createRobot();
  }

}
