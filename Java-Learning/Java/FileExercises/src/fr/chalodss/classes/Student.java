package fr.chalodss.classes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Charles T.
 * 
 */
public final class Student {

  private final String    name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private final LocalDate birthDate;
  private int             note;

  public Student() {
    name      = "";
    birthDate = LocalDate.now();
  }

  public Student(String name, LocalDate birthDate, int note) {
    this.name      = name;
    this.birthDate = birthDate;
    this.note      = note;
  }

  @Override
  public String toString() {
    return "[" + name + ", " + birthDate + ", " + note + "]";
  }

  public int getNote() {
    return note;
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setNote(int note) {
    this.note = note;
  }

}
