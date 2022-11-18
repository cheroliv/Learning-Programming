package fr.chalodss.classes;

import static fr.chalodss.utils.Images.*;
import static fr.chalodss.classes.Collisions.*;
import static fr.chalodss.classes.EState.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Charles T.
 *
 */
public final class Game {

  final BooleanProperty end       = new SimpleBooleanProperty(false);
  final IntegerProperty score     = new SimpleIntegerProperty(0);

  final Player          player;
  final List<Entity>    invaders;
  final List<Entity>    walls;
  final List<Entity>    beams;

  final Random          rand      = new Random();

  int                   direction = 0;
  double                deltaX    = 1;


  public Game(Player player, List<Entity> invaders, List<Entity> walls) {
    this.player   = player;
    this.invaders = invaders;
    this.walls    = walls;
    this.beams    = new LinkedList<>();
  }

  void handlePlayer() {
    if (player.getX() >= 7 && player.getX() <= 950) {
      player.move(player.velX);
    }
  }

  void updateInvaders() {
    invadersMove();
    invadersShoot();
    updateSpeedInvaders();
  }

  void invadersMove() {
    if ((direction & 1) == 0) {
      invaders.forEach(invader -> invader.moveX(deltaX));
    } else {
      invaders.forEach(invader -> invader.moveY(15));
      deltaX = -deltaX;
    }
    for (Entity invader : invaders) {
      if (invader.getX() <= 50 || invader.getX() >= 900) {
        direction ^= 1;
        break;
      }
    }
  }

  void updateSpeedInvaders() {
    if (invaders.size() > 33) {
      deltaX = (deltaX < 0) ? -1 : 1;
    } else if (invaders.size() > 11) {
      deltaX = (deltaX < 0) ? -2 : 2;
    } else if (invaders.size() > 5) {
      deltaX = (deltaX < 0) ? -5 : 5;
    } else if (invaders.size() > 1) {
      deltaX = (deltaX < 0) ? -10 : 10;
    } else {
      deltaX = (deltaX < 0) ? -25 : 25;
    }
  }

  void invadersShoot() {
    invaders.forEach(i -> {
      if (beams.size() < 5 && rand.nextInt(55) == 0) {
        var beam = new Entity(i.getX() + 28, i.getY() + 50, BEAM_INVADER);
        beams.add(beam);
      }
    });
  }

  void updateBeams() {
    updateBeamPlayer();
    updateBeamsEnnemies();
  }

  private void updateBeamPlayer() {
    if (player.beam.getY() < 0) {
      player.beam.setY(0);
      player.beam.setX(-20);
      player.canShoot = true;
    } else if (player.beam.getY() > 0) {
      player.beam.moveY(-30);
    }
  }

  private void updateBeamsEnnemies() {
    beams.forEach(beam -> {
      if (beam.getY() > 700) {
        beam.state = DEAD;
      } else {
        beam.setY(beam.getY() + 5);
      }
    });
    beams.removeIf(Entity::isDead);
  }

  void updateCollisions() {
    collisionBeamEnnemiesWalls(beams, walls);
    collisionBeamPlayerWalls(player, walls);
    collisionBeamEnnemiesPlayer(beams, player, end);
    collisionBeamPlayerEnnemies(player, invaders, score);
  }

}
