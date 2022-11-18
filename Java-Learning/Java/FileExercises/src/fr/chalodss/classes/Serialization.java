package fr.chalodss.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Charles T.
 *
 */
public final class Serialization {

  private Serialization() {

  }

  public static void saveObject(Serializable data, String file) throws IOException {
    try (var out = new ObjectOutputStream(Files.newOutputStream(Paths.get(file)))) {
      out.writeObject(data);
    }
  }

  public static Object load(String file) throws IOException, ClassNotFoundException {
    try (var in = new ObjectInputStream(Files.newInputStream(Paths.get(file)))) {
      return in.readObject();
    }
  }

}
