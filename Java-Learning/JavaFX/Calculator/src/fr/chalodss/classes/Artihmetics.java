package fr.chalodss.classes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Charles T.
 * 
 */
public final class Artihmetics {

  private static MathContext mc = new MathContext(3, RoundingMode.HALF_UP);

  private Artihmetics() {

  }

  static BigDecimal add(BigDecimal a, BigDecimal b) {
    return a.add(b);
  }

  static BigDecimal subtract(BigDecimal a, BigDecimal b) {
    return a.subtract(b);
  }

  static BigDecimal multiply(BigDecimal a, BigDecimal b) {
    return a.multiply(b);
  }

  static BigDecimal divide(BigDecimal a, BigDecimal b) {
    return a.divide(b, mc);
  }

}
