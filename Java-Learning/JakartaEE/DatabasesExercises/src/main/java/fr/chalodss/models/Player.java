package fr.chalodss.models;

/**
 * @author Charles T.
 * 
 */
public final class Player {

  private int    id;
  private String firstName;
  private String lastName;
  private int    elo;

  public Player(int id, String firstName, String lastName, int elo) {
    this.id        = id;
    this.firstName = firstName;
    this.lastName  = lastName;
    this.elo       = elo;
  }

  @Override
  public String toString() {
    return "[" + id + ", " + firstName + ", " + lastName + ", " + elo + "]";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getElo() {
    return elo;
  }

  public void setElo(int elo) {
    this.elo = elo;
  }

}
