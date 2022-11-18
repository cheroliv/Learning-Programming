package fr.chalodss.app;

import static fr.chalodss.utils.Constants.*;
import static fr.chalodss.classes.SearchFunctions.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import fr.chalodss.classes.SearchAgent;

/**
 * @author Charles T.
 *
 */
public class Main {

  public static void main(String[] args) {
    var list        = IntStream.range(0, SIZE).boxed().toList();
    var valueToFind = new Random().nextInt(SIZE);

    System.out.println("Value to find: " + valueToFind + "\n");

    var l1 = list.stream().collect(Collectors.toList());
    Collections.shuffle(l1);
    System.out.println("---------- SIMPLE SEARCH ----------");
    simpleSearch(l1, valueToFind);
    System.out.println();

    var l2 = list.stream().collect(Collectors.toList());
    System.out.println("------- DICHOTOMOUS SEARCH --------");
    Collections.sort(l2);
    dichotomousSearch(l2, 0, l2.size(), valueToFind);
    System.out.println();

    var l3 = list.stream().toList();
    System.out.println("------ INTERPOLATION SEARCH -------");
    interpolationSearch(l3, 0, l3.size() - 1, valueToFind);
    System.out.println();

    var l4 = l1.stream().toList();
    System.out.println("----- PARALELL STREAM SEARCH ------");
    Optional<Integer> o = streamSearch(l4, valueToFind);
    System.out.println("\n" + (o.isEmpty() ? "No value found" : o.get()) + "\n");

    var l5     = l1.stream().toList();
    var agents = new Thread[CPU];
    var start  = 0;
    var end    = GAP - 1;

    for (var i = 0; i < CPU; i++) {
      agents[i]  = new Thread(new SearchAgent(l5, valueToFind, start, end));
      start      = end + 1;
      end       += GAP;
    }
    System.out.println("------- MULTITHREAD SEARCH --------");
    multiThreadSearch(agents, CPU);
  }

}
