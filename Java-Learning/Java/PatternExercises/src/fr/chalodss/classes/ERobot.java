package fr.chalodss.classes;

import fr.chalodss.interfaces.IRobot;

/**
 * @author Charles T.
 * 
 */
enum ERobot {

  T800 {

    T800 createRobot() {
      return new T800();
    }

  },

  T1000 {

    T1000 createRobot() {
      return new T1000();
    }

  };

  abstract IRobot createRobot();

}
