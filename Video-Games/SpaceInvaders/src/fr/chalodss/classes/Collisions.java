package fr.chalodss.classes;

import static fr.chalodss.classes.EState.*;

import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public final class Collisions {

  private Collisions() {

  }

  static boolean collide(Entity e1, Entity e2) {
    return e1.getBoundsInParent().intersects(e2.getBoundsInParent());
  }

  static boolean collide(Entity e1, Player player) {
    return e1.getBoundsInParent().intersects(player.getBoundsInParent());
  }

  static void collisionBeamEnnemiesPlayer(List<Entity> beams, Player player, BooleanProperty end) {
    if (beams.stream().filter(b -> collide(b, player)).count() > 0) {
      player.state = DEAD;
      end.set(true);
    }
  }

  static void collisionBeamEnnemiesWalls(List<Entity> beams, List<Entity> walls) {
    beams.stream().forEach(beam -> walls.stream().filter(wall -> collide(beam, wall)).forEach(wall -> {
      wall.state = DEAD;
      beam.state = DEAD;
    }));
    walls.removeIf(Entity::isDead);
    beams.removeIf(Entity::isDead);
  }

  static void collisionBeamPlayerEnnemies(Player player, List<Entity> invaders, IntegerProperty score) {
    if (player.beam.getY() > 0) {
      var op = invaders.stream().filter(i -> collide(i, player.beam)).findFirst();

      if (op.isPresent()) {
        Entity e = op.get();
        e.state = DEAD;
        player.beam.setY(-25);
        score.set(score.get() + 20);
        invaders.remove(e);
      }
    }
  }

  static void collisionBeamPlayerWalls(Player player, List<Entity> walls) {
    if (player.beam.getY() > 0) {
      var op = walls.stream().filter(w -> collide(w, player.beam)).findFirst();

      if (op.isPresent()) {
        Entity e = op.get();
        e.state = DEAD;
        player.beam.setY(-25);
        walls.remove(e);
      }
    }
  }

}
