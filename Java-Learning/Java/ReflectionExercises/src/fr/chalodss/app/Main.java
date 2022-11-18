package fr.chalodss.app;

import java.lang.reflect.InvocationTargetException;
import fr.chalodss.classes.Introspection;
import fr.chalodss.classes.MyList;
import fr.chalodss.classes.Person;

/**
 * @author Charles T.
 * 
 */
public class Main {

  private Integer nb1;
  private Double  nb2;
  private MyList  list;

  private static Object[] resizeArray(Object[] src, int size) {
    var dest = (Object[]) java.lang.reflect.Array.newInstance(src.getClass().getComponentType(), size);

    System.arraycopy(src, 0, dest, 0, src.length);
    return dest;
  }

  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    var p1     = new Person("Tintin", 30);
    var method = p1.getClass().getDeclaredMethod("speak", String.class);
    var cons   = Person.class.getConstructor(String.class, int.class);
    var p2     = cons.newInstance("Tryphon", 60);

    System.out.println("p1 = " + p1);
    System.out.println((p1 instanceof Person) + " " + (p1.getClass() == Person.class));
    method.invoke(p1, "testing invoke");
    System.out.println(p2 + " " + p2.getClass().getSimpleName());

    Integer[] t1 = {2, 3, 5, 7, 11};
    String[]  t2 = {"Cat", "Dog", "Horse"};

    t1 = (Integer[]) Main.resizeArray(t1, 10);
    t2 = (String[]) Main.resizeArray(t2, 5);

    for (var i : t1) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (var s : t2) {
      System.out.print(s + " ");
    }

    System.out.println("\n");
    Introspection.display(Class.forName("fr.chalodss.app.Main"), "");
    System.out.println();
  }

  public Integer getNb1() {
    return nb1;
  }

  public void setNb1(Integer nb1) {
    this.nb1 = nb1;
  }

  public Double getNb2() {
    return nb2;
  }

  public void setNb2(Double nb2) {
    this.nb2 = nb2;
  }

  public MyList getList() {
    return list;
  }

  public void setList(MyList list) {
    this.list = list;
  }

}
