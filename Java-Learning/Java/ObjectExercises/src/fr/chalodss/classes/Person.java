package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class Person {

  private String  firstName;
  private String  lastName;
  private int     age;
  private EGender gender;

  public Person() {

  }

  public Person(String firstName, String lastName, int age, EGender gender) {
    this.firstName = firstName;
    this.lastName  = lastName;
    this.age       = age;
    this.gender    = gender;
  }

  @Override
  public String toString() {
    return "[" + firstName + " " + lastName + " " + age + " " + gender + "]";
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public EGender getGender() {
    return gender;
  }

  public void setGender(EGender gender) {
    this.gender = gender;
  }

}
