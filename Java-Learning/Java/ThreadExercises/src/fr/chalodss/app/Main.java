package fr.chalodss.app;

import static fr.chalodss.classes.TestBlockingCall.*;
import static fr.chalodss.classes.TestMonitor.*;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    switch (args[0]) {
      case "1" -> testBlockingCall();
      case "2" -> testMonitor();
      default -> throw new IllegalArgumentException();
    }
  }

}
