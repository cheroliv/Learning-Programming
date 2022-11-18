package fr.chalodss.classes;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Charles T.
 * 
 */
public final class Streams {

  private Streams() {

  }

  public static void hello() {
    Stream.of("hello").forEach(out::print);
  }

  public static void displayNumbers(int limit) {
    Stream.iterate(0, n -> n + 1).limit(limit).forEach(e -> out.print(e + " "));
  }

  // public static void displayAlphabet() {
  // Stream.iterate('a', c -> ++c).limit(26).forEach(out::print);
  // }

  public static void displayAlphabet() {
    IntStream.rangeClosed('a', 'z').forEach(c -> out.print((char) c));
  }

  // public static int myAbs(int nb) {
  // return IntStream.of(nb).map(n -> (n < 0) ? -n : n).findFirst().getAsInt();
  // }

  public static int myAbs(int nb) {
    return IntStream.of(nb).map(Math::abs).findFirst().getAsInt();
  }

  public static void displayTab(int[] tab) {
    Arrays.stream(tab).forEach(out::print);
  }

  public static int maxTab(int[] tab) {
    return Arrays.stream(tab).max().getAsInt();
  }

  public static double mean(double... values) {
    return Arrays.stream(values).average().getAsDouble();
  }

  public static long countVowels(String str) {
    return str.chars().filter(c -> "aeiouy".contains(Character.toString(c))).count();
  }

  public static long countConsonants(String str) {
    return str.chars().filter(c -> Pattern.matches("[zrtpqsdfghjklmwxcvbn]", Character.toString(c))).count();
  }

  public static void displayMultTables(int n) {
    IntStream.rangeClosed(1, n).forEach(u -> {
      IntStream.rangeClosed(1, 10).forEach(v -> out.printf("%4d", u * v));
      out.println();
    });
  }

  public static boolean containsUpperCase(String str) {
    return str.chars().anyMatch(Character::isUpperCase);
  }

  // public static List<Integer> sortTab(Integer[] tab) {
  // return Stream.of(tab).sorted().toList();
  // }

  public static <T> List<T> sortTab(T[] tab, Comparator<T> c) {
    return Stream.of(tab).sorted(c).toList();
  }

  // public static boolean isPalindrome(String word) {
  // return Stream.of(word)
  // .filter(w -> w.equals(new StringBuilder(word).reverse().toString()))
  // .count() > 0;
  // }

  public static boolean isPalindrome(String str) {
    return IntStream.range(0, str.length() / 2).allMatch(i -> str.charAt(i) == str.charAt(str.length() - i - 1));
  }

  public static long factorial(int nb) {
    return IntStream.rangeClosed(2, nb).reduce(1, (x, y) -> x * y);
  }

  // public static Optional<Integer> fibonacci(int nb) {
  // return Stream.iterate(new int[] { 0, 1 }, e -> new int[] { e[1], e[0] + e[1] })
  // .skip(--nb)
  // .map(e -> e[0])
  // .findFirst();
  // }

  public static Integer fibonacci(int nb) {
    return Stream.iterate(new int[] {0, 1}, e -> new int[] {e[1], e[0] + e[1]})
        .limit(nb).reduce((a, b) -> b).map(e -> e[0])
        .orElseThrow(IllegalArgumentException::new);
  }

  public static <T> List<T> filterList(List<T> list, Predicate<T> p) {
    return list.stream().filter(p).toList();
  }

  // public static Integer sumSquare(int nb) {
  // return IntStream.rangeClosed(0, nb).filter(e -> e % 2 == 0).map(e -> e * e).sum();
  // }

  // public static Integer sumSquare(int nb) {
  // return IntStream.rangeClosed(2, nb).filter(e -> (e & 1) == 0).map(e -> e * e).sum();
  // }

  public static Integer sumSquare(int nb) {
    return IntStream.iterate(2, n -> n + 2).limit(nb / 2).map(n -> n * n).sum();
  }

  public static Map<Boolean, List<Integer>> partitioningList(List<Integer> list, Predicate<Integer> p) {
    return list.stream().collect(Collectors.partitioningBy(p));
  }

  public static <T, U> Map<T, Long> groupByFunction(Stream<U> s, Function<U, T> function) {
    return s.collect(Collectors.groupingBy(function, Collectors.counting()));
  }

  public static IntStream primeFactors(int nb) {
    return IntStream.range(2, nb).filter(e -> nb % e == 0)
        .mapToObj(e -> IntStream.concat(IntStream.of(e), primeFactors(nb / e)))
        .findFirst().orElse(IntStream.of(nb));
  }

}
