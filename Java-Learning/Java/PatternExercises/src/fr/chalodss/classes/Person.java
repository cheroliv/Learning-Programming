package fr.chalodss.classes;

import java.time.LocalDate;

/**
 * @author Charles T.
 * 
 */
public final class Person {

  private final String    name;
  private final LocalDate birthDate;
  private String          mail;
  private String          phone;

  public static class PersonBuilder {

    private final String    name;
    private final LocalDate birthDate;
    private String          mail;
    private String          phone;

    public PersonBuilder(String name, LocalDate birthDate) {
      this.name      = name;
      this.birthDate = birthDate;
    }

    public PersonBuilder setMail(String mail) {
      this.mail = mail;
      return this;
    }

    public PersonBuilder setPhone(String phone) {
      this.phone = phone;
      return this;
    }

    public Person build() {
      return new Person(this);
    }

  }

  public Person(PersonBuilder pBuilder) {
    this.name      = pBuilder.name;
    this.birthDate = pBuilder.birthDate;
    this.mail      = pBuilder.mail;
    this.phone     = pBuilder.phone;
  }

  @Override
  public String toString() {
    return "[" + name + " " + birthDate + " " + mail + " " + phone + "]";
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getmail() {
    return mail;
  }

  public String getPhone() {
    return phone;
  }

}
