package fr.chalodss.interfaces;

/**
 * @author Charles T.
 *
 */

@FunctionalInterface
public interface IFunction<T> {

  public abstract T compute(T elem);

}
