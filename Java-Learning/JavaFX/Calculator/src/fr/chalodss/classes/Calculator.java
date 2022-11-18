package fr.chalodss.classes;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Charles T.
 * 
 */
public final class Calculator {

  static StringProperty res = new SimpleStringProperty("");

  private Calculator() {

  }

  static void evaluate(String expr) {
    var pattern  = Pattern.compile("-?[0-9.]+|[-+*/()]");
    var match    = pattern.matcher(expr);
    var elements = new ArrayList<String>();

    while (match.find())
      elements.add(match.group());

    var postfixExpr = infixToPostfix(elements);
    var eval        = postfix(postfixExpr);

    res.set(eval);
  }

  private static List<String> infixToPostfix(List<String> expr) {
    List<String>  res   = new ArrayList<>();
    Deque<String> stack = new ArrayDeque<>();

    for (var elem : expr) {
      if (Pattern.matches("-?[0-9.]+", elem)) {
        res.add(elem);
      } else if ("-+/*".contains(elem)) {
        stack.push(elem);
      } else if (elem.equals(")")) {
        res.add(stack.pop());
      }
    }
    while (!stack.isEmpty()) {
      res.add(stack.pop());
    }
    return res;
  }

  private static String postfix(List<String> expr) {
    var stack = new ArrayDeque<String>();
    var res   = new BigDecimal(0);

    for (var elem : expr) {
      if ("+-*/".contains(elem)) {
        var op1 = new BigDecimal(stack.pop());
        var op2 = new BigDecimal(stack.pop());
        res = switch (elem) {
          case "+" -> Artihmetics.add(op1, op2);
          case "-" -> Artihmetics.subtract(op2, op1);
          case "*" -> Artihmetics.multiply(op1, op2);
          case "/" -> Artihmetics.divide(op2, op1);
          default -> res;
        };
        stack.push(String.valueOf(res));
      } else {
        stack.push(elem);
      }
    }
    return res.toString();
  }

}
