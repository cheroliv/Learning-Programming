package fr.chalodss.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Charles T.
 * 
 */
public final class TextFile {

  private TextFile() {

  }

  public static void readFileByCharacter(String file) throws IOException {
    try (var fis = new FileInputStream(new File(file))) {
      char current;
      while (fis.available() > 0) {
        current = (char) fis.read();
        System.out.print(current);
      }
    }
  }

  public static void readFileByLine(String file) throws IOException {
    var line = "";

    try (var bufferedReader = new BufferedReader(new FileReader(file))) {
      while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
      }
    }
  }

  public static void printFileContents(String file) throws IOException {
    try (Stream<String> s = Files.lines(Paths.get(file))) {
      s.forEach(System.out::println);
    }
  }

  public static void writeToFile(String file, String str) throws IOException {
    Files.write(Paths.get(file), str.getBytes());
  }

  public static void copy(String src, String dest) throws IOException {
    var buffer = new byte[32];
    var count  = 0;

    try (var in = new FileInputStream(src); var out = new FileOutputStream(dest);) {
      while ((count = in.read(buffer)) > 0) {
        out.write(buffer, 0, count);
      }
    }
  }

}
