package fr.chalodss.utils;

public final class Constants {

  private Constants() {

  }

  public static final String NBI  = "\nNumber of iterations: ";
  public static final String IND  = " indice: ";
  public static final String VTF  = " valueToFind: ";

  public static final int    SIZE = 16_000_000;
  public static final int    CPU  = Runtime.getRuntime().availableProcessors();
  public static final int    GAP  = SIZE / CPU;

}
