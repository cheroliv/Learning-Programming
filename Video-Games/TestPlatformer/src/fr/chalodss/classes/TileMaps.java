package fr.chalodss.classes;

import static fr.chalodss.classes.EMapType.*;
import static fr.chalodss.utils.Constants.*;

import java.util.ArrayList;
import java.util.List;


public final class TileMaps {

  protected static final String[]  map     = {"----------------------------------------",
                                              "----------------------------------------",
                                              "----------------------------------------",
                                              "---------------------------1-11---------",
                                              "----------2------------------31---------",
                                              "-----------------------------11---------",
                                              "--------------13--------2----21---------",
                                              "----------11--13---1---------11---------",
                                              "------11--11--12---1-------1-13---------",
                                              "--11--11--12--13---2-------2-12---------",
                                              "--11--11--12--21---1---------11---------",
                                              "0000000000000000000000000000000000000000"};

  protected static List<MapEntity> tileMap = new ArrayList<>();

  private TileMaps() {

  }

  public static void initMap() {
    for (var i = 0; i < 12; i++) {
      for (var j = 0; j < 40; j++) {
        var type = switch (map[i].charAt(j)) {
          case '0'  -> GRASS;
          case '1'  -> BLUEBALL;
          case '2'  -> REDBALL;
          case '3'  -> YELLOWSQUARE;
          default   -> EMPTY;
        };
        tileMap.add(new MapEntity(j * TILE_SIZE, i * TILE_SIZE, type));
      }
    }
  }

}
