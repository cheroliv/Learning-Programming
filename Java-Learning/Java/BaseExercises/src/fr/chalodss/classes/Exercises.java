package fr.chalodss.classes;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Scanner;

public class Exercises {

  private Exercises() {

  }

  public static void hello() {
    System.out.println("hello");
  }

  public static void displayNumbers(int limit) {
    for (var i = 0; i <= limit; i++) {
      System.out.print(i + " ");
    }
  }

  public static void displayAlphabet() {
    for (var c = 'a'; c <= 'z'; c++) {
      System.out.print(c);
    }
  }

  public static void displayMultTables(int n) {
    for (var i = 1; i <= n; i++) {
      for (var j = 1; j <= 10; j++) {
        System.out.printf("%4d ", i * j);
      }
      System.out.println();
    }
  }

  public static int max(int i, int j, int k) {
    var m = i;

    if (m < j) {
      m = j;
    }
    if (m < k) {
      m = k;
    }
    return m;
  }

  public static void getMultipleNumber(int n) {
    var sc = new Scanner(System.in);
    var nb = 0;

    do {
      System.out.println("Give an integer:");
      nb = sc.nextInt();
    } while ((nb % n) != 0);
    System.out.println("Your number is: " + nb);
    sc.close();
  }

  public static int myAbs(int n) {
    return (n < 0) ? -n : n;
  }

  public static long factorial(int n) {
    var res = 1;

    for (var i = 2; i <= n; i++) {
      res *= i;
    }
    return res;
  }

  public static void displayDigits(int n) {
    if (n < 10) {
      System.out.print(n + " ");
    } else {
      displayDigits(n / 10);
      System.out.print(n % 10 + " ");
    }
  }

  public static void displayFibonacci(int n) {
    var nb1  = 0;
    var nb2  = 1;
    var fibo = 0;

    for (var i = 0; i < n; i++) {
      System.out.print(((i & 1) == 0) ? nb1 + " " + nb2 + " " : "");
      fibo = nb1 + nb2;
      nb1  = nb2;
      nb2  = fibo;
    }
  }

  public static double[] quadraticEquation(double a, double b, double c) {
    var tab          = new double[] {Double.NaN, Double.NaN};
    var discriminant = b * b - 4 * a * c;

    if (discriminant == 0) {
      tab[0] = -b / 2 * a;
    } else if (discriminant > 0) {
      tab[0] = (-b - Math.sqrt(discriminant)) / (2 * a);
      tab[1] = (-b + Math.sqrt(discriminant)) / (2 * a);
    }
    return tab;
  }

  public static long gcd(long a, long b) {
    return (b == 0) ? a : gcd(b, a % b);
  }

  public static boolean isPerfectNumber(int n) {
    var res = 0;

    for (var i = 1; i < n / 2 + 1; i++) {
      res += (n % i) == 0 ? i : 0;
    }
    return res == n;
  }

  public static void displayPrimeFactors(int n) {
    while ((n & 1) == 0) {
      System.out.print(2 + " ");
      n /= 2;
    }
    for (var i = 3; i <= Math.sqrt(n); i += 2) {
      while (n % i == 0) {
        System.out.print(i + " ");
        n /= i;
      }
    }
    System.out.print((n > 2) ? n : "");
  }

  public static void displayTab(int[] tab) {
    for (var elem : tab) {
      System.out.print(elem + " ");
    }
  }

  public static int sumTab(int[] tab) {
    var sum = 0;

    for (var i = 0; i < tab.length; i++) {
      sum += tab[i];
    }
    return sum;
  }

  public static int maxTab(int[] tab) {
    var max = tab[0];

    for (var i = 1; i < tab.length; i++) {
      if (max < tab[i]) {
        max = tab[i];
      }
    }
    return max;
  }

  public static void swapValuesTab(int[] tab, int i, int j) {
    var tmp = tab[i];

    tab[i] = tab[j];
    tab[j] = tmp;
  }

  public static void arrayOccurrences(int[] tab, int max) {
    var tmp = new int[max];

    for (var i = 0; i < tab.length; i++) {
      tmp[tab[i]]++;
    }
    for (var i = 0; i < tmp.length; i++) {
      if (tmp[i] != 0) {
        System.out.printf("%d:\t %d occurrence(s)%n", i, tmp[i]);
      }
    }
  }

  public static int countLetters(String str, char letter) {
    var res = 0;

    for (var i = 0; i < str.length(); i++) {
      if (str.charAt(i) == letter) {
        ++res;
      }
    }
    return res;
  }

  public static int countVowels(String str) {
    var nbVowels = 0;

    for (var i = 0; i < str.length(); i++) {
      if ("aeiouy".contains(String.valueOf(str.charAt(i)))) {
        ++nbVowels;
      }
    }
    return nbVowels;
  }

  public static String convertToUpperCase(String str) {
    return str.toUpperCase();
  }

  public static boolean containsUpperCase(String str) {
    var i = 0;

    while (i < str.length() && (!Character.isUpperCase(str.charAt(i)))) {
      ++i;
    }
    return i < str.length();
  }

  public static boolean isInStr(String src, String str) {
    return src.contains(str);
  }

  public static boolean isPalindromeWord(String str) {
    var i = 0;
    var j = str.length() - 1;

    while (i < j) {
      if (str.charAt(i) != str.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static boolean isPalindromeSentence(String str, int start, int end) {
    if (str.charAt(start) == ' ') {
      return isPalindromeSentence(str, start + 1, end);
    } else if (str.charAt(end) == ' ') {
      return isPalindromeSentence(str, start, end - 1);
    } else if (start >= end) {
      return true;
    } else if (str.charAt(start) != str.charAt(end)) {
      return false;
    } else {
      return isPalindromeSentence(str, start + 1, end - 1);
    }
  }

  public static String convertBase(int n, int base) {
    var elements = "0123456789ABCDEF";
    var res      = new StringBuilder();

    do {
      res.append(elements.charAt(n % base));
      n /= base;
    } while (n != 0);
    return res.reverse().toString();
  }

  public static String cesarCipher(String str, int n) {
    var  sb   = new StringBuilder();
    var  keys = "abcdefghijklmnopqrstuvwxyz";
    char c    = 0;

    for (var i = 0; i < str.length(); i++) {
      c = str.charAt(i);
      sb.append(keys.charAt((c - 97 + n) % 26));
    }
    return sb.toString();
  }

  public static Integer getItemMap(Map<String, Integer> map, String key) {
    return map.get(key);
  }

  public static int postfix(String expr) {
    var elements = expr.split(" ");
    var stack    = new ArrayDeque<String>();
    var res      = 0;

    for (var elem : elements) {
      if ("+-*/".contains(elem)) {
        var op1 = Integer.parseInt(stack.pop());
        var op2 = Integer.parseInt(stack.pop());
        res = switch (elem) {
          case "+" -> op2 + op1;
          case "-" -> op2 - op1;
          case "*" -> op2 * op1;
          case "/" -> op2 / op1;
          default -> res;
        };
        stack.push(String.valueOf(res));
      } else {
        stack.push(elem);
      }
    }
    return res;
  }

}
