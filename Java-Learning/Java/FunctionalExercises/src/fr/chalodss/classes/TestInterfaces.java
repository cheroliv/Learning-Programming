package fr.chalodss.classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.stream.Stream;
import fr.chalodss.interfaces.IFunction;

public final class TestInterfaces {

  private TestInterfaces() {

  }

  private static Integer f1(Integer n) {
    return n + 2;
  }

  private static Integer f2(Integer n1, Integer n2) {
    return n1 + n2;
  }

  private static Integer f3(Integer n1, Integer n2, String op) {
    return switch (op) {
      case "+" -> n1 + n2;
      case "-" -> n1 - n2;
      case "*" -> n1 * n2;
      case "/" -> n1 / n2;
      default -> n1;
    };
  }

  private static Integer f4(Integer n, IFunction<Integer> f) {
    return f.compute(n);
  }

  private static List<Integer> f5(List<Integer> list, IFunction<Integer> f) {
    return list.stream().map(f::compute).toList();
  }

  public static void testFunctional() {
    System.out.println(f1(5));
    System.out.println(f2(7, 2));
    System.out.println(f3(5, 7, "+"));
    System.out.println(f3(5, 7, "*"));
    System.out.println(f4(15, n -> n + 2));
    System.out.println(f4(-101, Math::abs));

    var list = Arrays.asList(5, 4, 3, 2, 1);

    list = f5(list, n -> n + 1);
    System.out.println(list);
  }

  public static void testPredicate() {
    IntPredicate p = n -> (n & 1) == 0;

    System.out.println(p.test(8));
  }

  public static void testUnaryOperator() {
    IntUnaryOperator uOp = n -> n * n;

    System.out.println(uOp.applyAsInt(8));
  }

  public static void testConsumer() {
    var                    words = Arrays.asList("ordinateur", "planète", "animal", "bit");
    var                    map   = new HashMap<String, Integer>();
    ObjIntConsumer<String> bc    = map::put;

    Stream.iterate(0, n -> n + 1).limit(words.size()).forEach(i -> bc.accept(words.get(i), i));
    System.out.println(map);
  }

  public static void testBiFunction() {
    var                                 word = "hello world";
    BiFunction<String, Integer, String> bf   = (str, i) -> str.concat(str.substring(i));

    word = bf.apply(word, 5);
    System.out.println(word);
  }

}
