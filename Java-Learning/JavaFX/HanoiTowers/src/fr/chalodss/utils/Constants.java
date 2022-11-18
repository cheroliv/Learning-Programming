package fr.chalodss.utils;

import java.util.Map;

/**
 * @author Charles T.
 *
 */
public final class Constants {

  public static final double                T1X = 300;
  public static final double                T2X = 600;
  public static final double                T3X = 900;

  public static final int                   DW  = 250;
  public static final double                SX  = 175;
  public static final double                SY  = 700;
  public static final Map<Integer, Integer> DH  = Map.of(0, 40,
                                                         1, 20,
                                                         2, 10,
                                                         4, 6,
                                                         8, 4);

  private Constants() {

  }

}
