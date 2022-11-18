package fr.chalodss.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @author Charles T.
 * 
 */
public final class AgeService {

  private AgeService() {

  }

  public static Period computeAge(String date) {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    var birthdate = LocalDate.parse(date, formatter);
    var now       = LocalDate.now();

    return Period.between(birthdate, now);
  }

}
