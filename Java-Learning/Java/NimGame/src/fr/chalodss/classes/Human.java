package fr.chalodss.classes;

import static fr.chalodss.utils.Colors.*;

/**
 * @author Charles T.
 * 
 */
public final class Human extends Player {

  public Human(String name) {
    super(name);
  }

  @Override
  int getMatches(int[] packets) {
    System.out.print(CYAN + "\nChoose your packet                     : " + RESET);
    var indice = Integer.parseInt(EScanner.get());
    System.out.print(CYAN + "Choose the number of matches to remove : " + RESET);
    var nbMatches = Integer.parseInt(EScanner.get());
    System.out.println();
    packets[indice] -= nbMatches;
    return nbMatches;
  }

  @Override
  public String toString() {
    return "Human " + name;
  }

}
