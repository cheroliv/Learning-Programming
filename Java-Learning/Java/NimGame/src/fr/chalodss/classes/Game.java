package fr.chalodss.classes;

import static fr.chalodss.classes.EMove.*;
import static fr.chalodss.utils.Colors.*;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Charles T.
 * 
 */
public final class Game {

  private final Player human;
  private final Player computer;
  private final Random rand;
  private EMove        move;
  private int[]        packets;
  private int          nbMatches;

  public Game(int nbPackets, Player human, Player computer) {
    this.rand     = new Random();
    this.move     = HUMAN;
    this.packets  = new int[nbPackets];
    this.human    = human;
    this.computer = computer;
    fillPackets();
  }

  public void run() {
    System.out.println("\n---------- START GAME ----------\n");
    displayPackets();

    move = Arrays.stream(packets).reduce((x, y) -> x ^ y).getAsInt() != 0 ? COMPUTER : HUMAN;
    System.out.println("\n" + move + " moves first.\n");
    do {
      nbMatches -= (move == HUMAN) ? human.getMatches(packets) : computer.getMatches(packets);
      displayPackets();
      move = (move == HUMAN) ? COMPUTER : HUMAN;
    } while (nbMatches >= 1);
    System.out.println(RED + (move == HUMAN ? computer + " won the game." : human + " won the game.") + RESET);
  }

  private void fillPackets() {
    for (var i = 0; i < packets.length; i++) {
      packets[i]  = rand.nextInt(5) + 1;
      nbMatches  += packets[i];
    }
  }

  private void displayPackets() {
    displayIndices();
    displayLine();

    for (var elem : packets) {
      if (elem != 0) {
        System.out.print("|");
        for (var i = 0; i < elem; i++) {
          System.out.print(GREEN + "|");
        }
        System.out.print(RESET + "|     ");
      } else {
        System.out.print("EMPTY     ");
      }
    }
    System.out.println();
    displayLine();
    System.out.println();
  }

  private void displayIndices() {
    for (var i = 0; i < packets.length; i++) {
      if (packets[i] != 0) {
        System.out.print(i);
        for (var j = 0; j < packets[i]; j++) {
          System.out.print(" ");
        }
        System.out.print("      ");
      } else {
        System.out.print("          ");
      }
    }
    System.out.println();
  }

  private void displayLine() {
    for (var elem : packets) {
      if (elem != 0) {
        System.out.print("-");
        for (var i = 0; i < elem; i++) {
          System.out.print("-");
        }
        System.out.print("-     ");
      } else {
        System.out.print("-----     ");
      }
    }
    System.out.println();
  }

}
