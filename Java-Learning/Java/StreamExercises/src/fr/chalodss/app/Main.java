package fr.chalodss.app;

import static fr.chalodss.classes.Streams.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author Charles T.
 *
 */
public class Main {

  public static void main(String[] args) {
    int[] t1 = {5, 2, 1, 3, 4};

    hello();
    System.out.println();
    displayNumbers(20);
    System.out.println();
    displayAlphabet();
    System.out.println();
    System.out.println(myAbs(-8));
    displayTab(t1);
    System.out.println();
    System.out.println(maxTab(t1));
    System.out.println(mean(10, 15, 25, 45));
    System.out.println(countVowels("ordinateur"));
    System.out.println(countConsonants("univers"));
    displayMultTables(10);

    Integer[]         t  = {5, 2, 1, 3, 4};
    Predicate<String> p  = s -> s.length() >= 7;
    var               l1 = Arrays.asList("Charles", "Lancelot", "Archimède", "Eliott");
    var               l2 = Arrays.asList(2, 3, 1, 5, 4);

    System.out.println(containsUpperCase("chArles"));
    System.out.println(sortTab(t, (n1, n2) -> n1 - n2));
    System.out.println(isPalindrome("radar"));
    System.out.println(sumSquare(5));
    System.out.println(factorial(5));
    System.out.println(fibonacci(8));
    System.out.println(filterList(l1, p));
    System.out.println(partitioningList(l2, e -> e > 2));
    System.out.println(groupByFunction(l1.stream(), String::length));
    System.out.println("Prime factors of 8: " + primeFactors(8).boxed().toList());
    System.out.println("Prime factors of 13: " + primeFactors(13).boxed().toList());
    System.out.println("Prime factors of 2500: " + primeFactors(2500).boxed().toList());
    System.out.println("Test on 10 000 numbers without parallelization");
    var s1 = Instant.now();
    IntStream.range(1, 100000).forEach(e -> primeFactors(e).boxed().toList());
    var s2 = Instant.now();
    System.out.println(Duration.between(s1, s2).toMillis() + "ms");
    System.out.println("Test on 10 000 numbers with parallelization");
    var s3 = Instant.now();
    IntStream.range(1, 100000).parallel().forEach(e -> primeFactors(e).boxed().toList());
    var s4 = Instant.now();
    System.out.println(Duration.between(s3, s4).toMillis() + "ms");
  }

}
