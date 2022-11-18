package fr.chalodss.classes;

import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Charles T.
 * 
 */

public final class JsonFile {

  private JsonFile() {

  }

  public static String convertObjectToJsonString(Object o) throws JsonProcessingException {
    var om = new ObjectMapper();

    om.registerModule(new JavaTimeModule());
    return om.writeValueAsString(o);
  }

  public static Object readObjectFromJsonFile(String fileName, Class<?> c) throws IOException {
    var om = new ObjectMapper();

    om.registerModule(new JavaTimeModule());
    return om.readValue(Paths.get(fileName).toFile(), c);
  }

}
