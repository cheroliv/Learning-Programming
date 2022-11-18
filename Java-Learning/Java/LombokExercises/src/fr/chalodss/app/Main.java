package fr.chalodss.app;

import java.util.Arrays;
import java.util.stream.Stream;
import fr.chalodss.classes.Warrior;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    var w1 = Warrior.of("w1", 110.0, Arrays.asList(new Object()));
    var w2 = Warrior.of("w2", 120.0, Arrays.asList(new Object()));

    Stream.of(w1, w2).forEach(Warrior::speak);
  }

}
