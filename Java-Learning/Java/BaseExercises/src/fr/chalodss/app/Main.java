package fr.chalodss.app;

import static fr.chalodss.classes.Exercises.*;

import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    hello();

    displayNumbers(20);
    System.out.println();

    displayAlphabet();
    System.out.println();

    displayMultTables(7);

    System.out.println(max(2, 1, 3));

    getMultipleNumber(8);

    System.out.println(myAbs(-8));

    System.out.println(factorial(10));

    displayDigits(65535);
    System.out.println();

    displayFibonacci(10);
    System.out.println();

    var t = quadraticEquation(1, -1, -1);
    System.out.println(t[0] + " " + t[1]);

    System.out.println(gcd(28, 4294967296L));

    System.out.println(isPerfectNumber(33_550_336));

    displayPrimeFactors(65535);
    System.out.println();

    var t1 = new int[] {1, 2, 3, 4, 5, 6, 7};
    displayTab(t1);
    System.out.println();

    System.out.println(sumTab(t1));

    System.out.println(maxTab(t1));

    swapValuesTab(t1, 2, 6);
    IntStream.range(0, t1.length).forEach(i -> System.out.print(t1[i] + " "));
    System.out.println();

    var t2 = new int[] {2, 1, 3, 4, 4, 5, 6, 4, 7, 1, 8, 9, 5, 18, 7, 1};
    arrayOccurrences(t2, 19);

    System.out.println(countLetters("mémoire", 'm'));

    System.out.println(countVowels("ordinateur"));

    System.out.println(convertToUpperCase("quantique"));

    System.out.println(containsUpperCase("cordeS"));

    System.out.println(isInStr("ordinateur", "ina"));

    System.out.println(isPalindromeWord("radar"));

    var str = "engage le jeu que je le gagne";
    System.out.println(isPalindromeSentence(str, 0, str.length() - 1));

    System.out.println(convertBase(2500, 8));

    System.out.println(cesarCipher("abcdefghijklmnopqrstuvwxyz", 4));

    var map = Map.of("Charles", 1500, "Archimède", 2850);
    System.out.println(getItemMap(map, "Archimède"));

    System.out.println(postfix("3 12 3 - 3 / 1 - *"));
  }

}
