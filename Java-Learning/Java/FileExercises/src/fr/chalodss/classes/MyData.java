package fr.chalodss.classes;

import java.io.Serializable;

/**
 * @author Charles T.
 * 
 */
public final class MyData implements Serializable {

  static final long       serialVersionUID = 1L;

  public String           str;
  public int              id;
  public static char      c;
  public transient double nb;

  public MyData() {

  }

}
