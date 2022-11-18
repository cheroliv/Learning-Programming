package fr.chalodss.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Charles T.
 * 
 */
@Path("/AgeService")
@Produces(MediaType.TEXT_PLAIN)
public final class AgeService {

  @POST
  public String computeAge(@FormParam("birthdate") String birthDayDate) {
    var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    var date1             = LocalDate.parse(birthDayDate, dateTimeFormatter);
    var date2             = LocalDate.now();

    return "You are " + Period.between(date1, date2).getYears() + " years old.";
  }

}
