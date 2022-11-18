package fr.chalodss.classes;

import static fr.chalodss.utils.Images.*;

import java.util.LinkedList;
import java.util.List;

public final class Init {

  private Init() {

  }

  static List<Entity> initInvaders(int x, int y) {
    List<Entity> list = new LinkedList<>();

    for (var i = 1; i <= 60; i++) {
      if (i % 12 == 0) {
        x  = 60;
        y += 60;
      } else if (i <= 12) {
        list.add(new Entity(x, y, SQUID));
      } else if (i <= 36) {
        list.add(new Entity(x, y, CRAB));
      } else if (i <= 60) {
        list.add(new Entity(x, y, OCTOPUS));
      }
      x += (i % 12 != 0) ? 70 : 0;
    }
    return list;
  }

  static List<Entity> initWalls(int x, int y) {
    List<Entity> list = new LinkedList<>();

    for (var i = 0; i < 4; i++) {
      for (var j = 1; j <= 15; j++) {
        if (x == 300) {
          x = 400;
        } else if (x == 600) {
          x = 700;
        }
        list.add(new Entity(x, y, WALL));
        x += 40;
      }
      x  = 100;
      y += 30 - 1;
    }
    return list;
  }

}
