package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class MinMax<T> {

  private T min;
  private T max;

  public MinMax(T min, T max) {
    this.min = min;
    this.max = max;
  }

  public T getMin() {
    return min;
  }

  public T getMax() {
    return max;
  }

}
