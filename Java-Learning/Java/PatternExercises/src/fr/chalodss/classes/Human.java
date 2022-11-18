package fr.chalodss.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles T.
 * 
 */
public final class Human {

  private final String       name;
  private final List<String> favoriteInterests;

  public Human(String name, List<String> favoriteInterests) {
    this.name = name;
    if (favoriteInterests == null) {
      throw new RuntimeException("favorite interests required");
    }
    this.favoriteInterests = new ArrayList<>(favoriteInterests);
  }

  public String getLastName() {
    return name;
  }

  public int getFavoriteInterestsCount() {
    return this.favoriteInterests.size();
  }

  public String getFavoriteInterest(int index) {
    return this.favoriteInterests.get(index);
  }

}
