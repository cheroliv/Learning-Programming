package fr.chalodss.classes;

import static fr.chalodss.utils.Colors.*;
import java.util.Arrays;

/**
 * @author Charles T.
 * 
 */
public final class Computer extends Player {

  public Computer(String name) {
    super(name);
  }

  @Override
  int getMatches(int[] packets) {
    for (var i = 0; i < packets.length; i++) {
      for (var j = 1; j <= packets[i]; j++) {
        packets[i] -= j;
        if (Arrays.stream(packets).filter(e -> e >= 0).reduce((x, y) -> x ^ y).getAsInt() == 0) {
          System.out.println(RED + name + " takes " + j + " matches(s) in packet number " + i + "\n" + RESET);
          return j;
        }
        packets[i] += j;
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    return "Computer " + name;
  }

}
