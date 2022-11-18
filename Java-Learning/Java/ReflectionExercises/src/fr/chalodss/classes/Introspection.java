package fr.chalodss.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author Charles T.
 * 
 */
public final class Introspection {

  private static Queue<Class<?>> classSet   = new ArrayDeque<>();
  private static Set<String>     packageSet = new HashSet<>();

  static {
    classSet.add(int.class);
    classSet.add(char.class);
    classSet.add(double.class);
  }

  static {
    packageSet.add("java.lang");
    packageSet.add("java.util");
    packageSet.add("java.security");
  }

  private Introspection() {

  }

  public static void display(Class<?> aClass, String left) {
    var fields = aClass.getDeclaredFields();

    classSet.offer(aClass);
    System.out.println(left + aClass.getName());
    left += "\t";

    for (var attribut : fields) {
      if (!isInPackageSet(attribut.getType().getName()) && !classSet.contains(attribut.getType())) {
        display(attribut.getType(), left);
      } else {
        displayField(attribut, left);
      }
    }
  }

  private static boolean isInPackageSet(String type) {
    return packageSet.stream().filter(type::contains).count() > 0;
  }

  private static void displayField(Field attribut, String left) {
    switch (Modifier.toString(attribut.getModifiers())) {
      case "public" -> System.out.println(left + "[public] " + attribut.getName());
      case "private" -> System.out.println(left + "[private] " + attribut.getName());
      case "protected" -> System.out.println(left + "[protected] " + attribut.getName());
      default -> System.out.println(left + attribut.getName());
    }
  }

}
