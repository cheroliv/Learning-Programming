package fr.chalodss.tests;

import static fr.chalodss.classes.Streams.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Charles T.
 * 
 */
public final class StreamTests {

  static final PrintStream           standardOut  = System.out;
  static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeAll
  static void setUp() {
    System.setOut(new PrintStream(outputStream));
  }

  @AfterAll
  static void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  @DisplayName("testHello")
  void testHello() {
    hello();
    assertEquals("hello", outputStream.toString().trim());
    outputStream.reset();
  }

  @Test
  @DisplayName("testDisplayNumbers")
  void testDisplayNumbers() {
    displayNumbers(0);
    assertEquals("", outputStream.toString().trim());
    displayNumbers(5);
    assertEquals("0 1 2 3 4", outputStream.toString().trim());
    outputStream.reset();
  }

  @Test
  @DisplayName("testDisplayAlphabet")
  void testDisplayAlphabet() {
    displayAlphabet();
    assertEquals("abcdefghijklmnopqrstuvwxyz", outputStream.toString().trim());
    outputStream.reset();
  }

  @Test
  @DisplayName("testMyAbs")
  void testMyAbs() {
    assertTrue(myAbs(7) == 7);
    assertTrue(myAbs(-7) == 7);
    assertTrue(myAbs(0) == 0);
  }

  @Test
  @DisplayName("testDisplayTab")
  void testDisplayTab() {
    int[] t1 = {1, 2, 3, 4};

    displayTab(t1);
    assertEquals("1234", outputStream.toString().trim());
    outputStream.reset();

    int[] t2 = {};

    displayTab(t2);
    assertEquals("", outputStream.toString().trim());
    outputStream.reset();
  }

  @Test
  @DisplayName("testMaxTab")
  void testMaxTab() {
    int[] t1 = {5, 2, 8, -6, 0, 7};
    assertTrue(maxTab(t1) == 8);

    int[] t2 = {1};
    assertTrue(maxTab(t2) == 1);
  }

  @Test
  @DisplayName("testMean")
  void testMean() {
    assertTrue(mean(1) == 1);
    assertTrue(mean(10, 20, 30) == 20);
    assertTrue(mean(10, 15, 25, 45) == 23.75);
  }

  @Test
  @DisplayName("testCountVowels")
  void testCountVowels() {
    assertAll("Test countVowels", 
        () -> assertTrue(countVowels("") == 0, ""), 
        () -> assertTrue(countVowels("a") == 1, ""),
        () -> assertTrue(countVowels("Charles") == 2, ""), 
        () -> assertTrue(countVowels("testeur_") == 3, ""));
  }

  @Test
  @DisplayName("testCountConsonants")
  void testCountConsonants() {
    assertAll("Test countConsonants", 
        () -> assertTrue(countConsonants("") == 0, ""),
        () -> assertTrue(countConsonants("z") == 1, ""), 
        () -> assertTrue(countConsonants("Archimède") == 5, ""),
        () -> assertTrue(countConsonants("testeur_") == 4, ""));
  }

  @Test
  @DisplayName("testContainsUpperCase")
  void testContainsUpperCase() {
    assertAll("Test containsUpperCase", 
        () -> assertFalse(containsUpperCase(""), ""),
        () -> assertFalse(containsUpperCase("hello"), ""), 
        () -> assertTrue(containsUpperCase("charleS"), ""),
        () -> assertTrue(containsUpperCase("TesTeur_"), ""));
  }

  static Stream<Arguments> generateData() {
    Comparator<Integer> c1 = (n1, n2) -> n1 - n2;
    Comparator<String>  c2 = (s1, s2) -> s1.compareTo(s2);
    return Stream.of(Arguments.of(Arrays.asList(5, 2, 4, 3, 1).toArray(), c1, Arrays.asList(1, 2, 3, 4, 5)),
        Arguments.of(Arrays.asList("John", "Alan", "Kurt").toArray(), c2, Arrays.asList("Alan", "John", "Kurt")));
  }

  @ParameterizedTest
  @MethodSource("generateData")
  <T> void testSortTab(T[] t, Comparator<T> c, List<T> list) {
    assertEquals(sortTab(t, c), list);
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "a", "radar", "java avaj"})
  void testIsPalindrome(String word) {
    assertTrue(isPalindrome(word));
  }

  @Test
  @DisplayName("testFactorial")
  void testFactorial() {
    assertAll("Test factorial", 
        () -> assertTrue(factorial(-1) == 1, ""), 
        () -> assertTrue(factorial(0) == 1, ""),
        () -> assertTrue(factorial(1) == 1, ""), 
        () -> assertTrue(factorial(2) == 2, ""),
        () -> assertTrue(factorial(5) == 120, ""), 
        () -> assertTrue(factorial(10) == 3628800, ""));
  }

  @ParameterizedTest
  @CsvSource(value = {"1,0", "2,1", "3,1", "4,2", "5,3", "6,5", "7,8"})
  @DisplayName("test fibo 1")
  void testFibonacci(int in, int out) {
    assertEquals(fibonacci(in), out);
  }

  @Test
  @DisplayName("test fibo 2")
  void testFibonacci() {
    assertThrows(IllegalArgumentException.class, () -> fibonacci(-1));
  }

  @Test
  @DisplayName("testFilterList")
  void testFilterList() {
    var l1 = Arrays.asList("a", "aa", "aaa", "aaaa");
    var l2 = Arrays.asList("b", "bb", "bbb", "bbbb", "", "z");
    var l3 = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    assertAll("Test filterList", 
        () -> assertEquals(filterList(l1, s -> s.length() >= 3), Arrays.asList("aaa", "aaaa")),
        () -> assertEquals(filterList(l2, s -> s.length() <= 2), Arrays.asList("b", "bb", "", "z")),
        () -> assertEquals(filterList(l3, n -> n % 3 == 0), Arrays.asList(0, 3, 6, 9)));
  }

  @Test
  @DisplayName("testSumSquare")
  void testSumSquare() {
    assertAll("Test sumSquare", 
        () -> assertTrue(sumSquare(-1) == 0, ""), 
        () -> assertTrue(sumSquare(0) == 0, ""),
        () -> assertTrue(sumSquare(1) == 0, ""), 
        () -> assertTrue(sumSquare(2) == 4, ""), 
        () -> assertTrue(sumSquare(3) == 4, ""),
        () -> assertTrue(sumSquare(6) == 56, ""), 
        () -> assertTrue(sumSquare(10) == 220, ""));
  }

  @Test
  @DisplayName("testPartitioningList")
  void testPartitioningList() {
    assertAll("Test partitioningList",
        () -> assertEquals(partitioningList(Arrays.asList(2, 3, 1, 5, 4), e -> e > 2),
            Map.of(false, Arrays.asList(2, 1), true, Arrays.asList(3, 5, 4))),
        () -> assertEquals(partitioningList(Arrays.asList(2, 3, 1, 5, 4), e -> e > 0),
            Map.of(false, Arrays.asList(), true, Arrays.asList(2, 3, 1, 5, 4))));
  }

  @Test
  @DisplayName("testGroupByFunction")
  void testGroupByFunction() {
    var map1 = Map.of(7, 1L, 5, 2L, 8, 1L);
    var map2 = Map.of(true, 1L, false, 3L);
    var s1   = Stream.of("charles", "lancelot", "maeva", "laure");
    var s2   = Stream.of("", "a", "ab", "abc");

    assertAll("Test groupByFunction",
        () -> assertTrue(groupByFunction(s1, String::length).entrySet().stream()
            .allMatch(e -> e.getValue().equals(map1.get(e.getKey())))),
        () -> assertTrue(groupByFunction(s2, s -> s.matches("a")).entrySet().stream()
            .allMatch(e -> e.getValue().equals(map2.get(e.getKey())))));
  }

  @Test
  @DisplayName("testPrimeFactors")
  void testPrimeFactors() {
    assertAll("Test primeFactors", 
        () -> assertEquals(primeFactors(8).boxed().toList(), IntStream.of(2, 2, 2).boxed().toList()),
        () -> assertEquals(primeFactors(13).boxed().toList(), IntStream.of(13).boxed().toList()),
        () -> assertEquals(primeFactors(2500).boxed().toList(), IntStream.of(2, 2, 5, 5, 5, 5).boxed().toList()));
  }

}
