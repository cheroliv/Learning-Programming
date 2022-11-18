package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
public final class Game {

  private enum EMove {
    P1, P2
  }

  private final Player p1;
  private final Player p2;
  private int          score;
  private EMove        move;

  public Game(Player p1, Player p2) {
    this.p1    = p1;
    this.p2    = p2;
    this.score = 0;
    this.move  = EMove.P1;
  }

  public void run() {
    var roll = 0;

    System.out.println("---------------------------------------------");
    do {
      roll   = (move == EMove.P1) ? p1.throwDice() : p2.throwDice();
      score += roll;
      System.out.printf("%-10s throws the dice: %2d\t score is: %d%n", move == EMove.P1 ? p1 : p2, roll, score);
      move = (move == EMove.P1) ? EMove.P2 : EMove.P1;
    } while (score < 21);
    System.out.printf("%s won the game.%n", move == EMove.P2 ? p1 : p2);
  }

}
