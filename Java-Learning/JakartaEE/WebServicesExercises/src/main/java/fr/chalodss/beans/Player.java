package fr.chalodss.beans;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Charles T.
 * 
 */
@XmlRootElement
public final class Player {

  private String name;
  private int    elo;

  public Player() {

  }

  public Player(String name, int elo) {
    this.name = name;
    this.elo  = elo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getElo() {
    return elo;
  }

  public void setElo(int elo) {
    this.elo = elo;
  }

}
