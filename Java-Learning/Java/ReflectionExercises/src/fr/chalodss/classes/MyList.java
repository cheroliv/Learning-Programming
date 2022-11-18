package fr.chalodss.classes;

/**
 * @author Charles T.
 * 
 */
public final class MyList {

  protected Object value;
  protected MyList next;

  public MyList(Object x, MyList l) {
    value = x;
    next  = l;
  }

}
