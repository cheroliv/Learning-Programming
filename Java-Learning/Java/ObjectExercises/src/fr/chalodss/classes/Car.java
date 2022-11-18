package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
public class Car {

  private int price;

  public Car(int price) {
    if (price < 0) {
      throw new CarException("Price should be > 0.");
    } else {
      this.price = price;
    }
  }

  public int getPrice() {
    return price;
  }

}
