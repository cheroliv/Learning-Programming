package fr.chalodss.app;

import static fr.chalodss.utils.Constants.*;
import static fr.chalodss.classes.JsonFile.*;
import static fr.chalodss.classes.Serialization.*;
import static fr.chalodss.classes.TextFile.*;
import static fr.chalodss.classes.XmlFile.*;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fr.chalodss.classes.MyData;
import fr.chalodss.classes.Person;
import fr.chalodss.classes.Student;

import jakarta.xml.bind.JAXBException;

/**
 * @author Charles T.
 * 
 */
public class Main {

  private static void testTextFiles() throws IOException {
    readFileByCharacter(FILE_1);
    System.out.println();
    readFileByLine(FILE_1);
    System.out.println();
    printFileContents(FILE_2);
    System.out.println();
    writeToFile(FILE_2, "hello\nlittle programmer\n");
    copy(FILE_1, FILE_3);
  }

  private static void testSerialization() {
    var data = new MyData();

    data.id  = 7;
    data.str = "ours";
    MyData.c = 'c';
    data.nb  = 2.0;

    try {
      saveObject(data, DATA);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      var loadedData = (MyData) load(DATA);
      System.out.println(loadedData.id + " " + loadedData.str + " " + MyData.c + " " + loadedData.nb);
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void testXmlFiles() throws JAXBException {
    var p1 = new Person(1, "charles");
    var p2 = convertXmlToJava(Person.class, DATA_XML);

    convertJavaToXml(p1, PERSON_XML);
    System.out.println(p2);
  }

  private static void testJsonFiles() throws IOException {
    var f  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    var s1 = new Student("A", LocalDate.parse("1666-06-06", f), 12);

    System.out.println(convertObjectToJsonString(s1));
    System.out.println(readObjectFromJsonFile(STUDENT_JSON, Student.class));
  }

  public static void main(String[] args) {
    try {
      testTextFiles();
      testSerialization();
      System.out.println();
      testXmlFiles();
      System.out.println();
      testJsonFiles();
    } catch (IOException | JAXBException e) {
      e.printStackTrace();
    }
  }

}
