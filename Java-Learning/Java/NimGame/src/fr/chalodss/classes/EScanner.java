package fr.chalodss.classes;

import java.util.Scanner;

/**
 * @author Charles T.
 * 
 */
public enum EScanner {
  ;

  private static final Scanner sc = new Scanner(System.in);

  public static String get() {
    return sc.nextLine();
  }

}
