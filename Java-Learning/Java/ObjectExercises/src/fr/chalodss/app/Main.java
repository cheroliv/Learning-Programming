package fr.chalodss.app;

import java.util.Arrays;
import fr.chalodss.classes.Aircraft;
import fr.chalodss.classes.Animal;
import fr.chalodss.classes.Bear;
import fr.chalodss.classes.Car;
import fr.chalodss.classes.EGender;
import fr.chalodss.classes.Mammal;
import fr.chalodss.classes.Person;
import fr.chalodss.classes.Robot;
import fr.chalodss.classes.SubMarine;
import fr.chalodss.classes.T1000;
import fr.chalodss.classes.T800;
import fr.chalodss.classes.TestGen;
import fr.chalodss.interfaces.IMove;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    var p1   = new Person("Bill", "Gates", 63, EGender.MAN);
    var p2   = new Person("Linus", "Torvalds", 49, EGender.MAN);
    var p3   = new Person("Bjarne", "Stroustrup", 68, EGender.MAN);
    var list = Arrays.asList(p1, p2, p3);

    list.forEach(System.out::println);

    var animal = new Animal();
    System.out.println("----------");
    var mammal = new Mammal(200, "ursidae");
    System.out.println("----------");
    var bear = new Bear(200, "ursidae", "Bart");

    System.out.println(animal);
    System.out.println(mammal);
    System.out.println(bear.getMass() + " kg.");

    Arrays.asList(new T800(80001), new T1000(100001)).forEach(Robot::attack);

    Arrays.asList(new Aircraft(2), new SubMarine(4)).forEach(IMove::move);

    Integer[] t1 = {5, 2, 3, 4, 1};
    String[]  t2 = {"ordinateur", "planète", "animal", "végétal", "bit"};

    System.out.println(TestGen.maxInt(t1) + "\t" + TestGen.minInt(t1));
    System.out.println(TestGen.maxStr(t2) + "\t" + TestGen.minStr(t2));
    System.out.println(TestGen.minMax(t1).getMax() + "\t" + TestGen.minMax(t1).getMin());
    System.out.println(TestGen.minMax(t2).getMax() + "\t" + TestGen.minMax(t2).getMin());

    new Car(-15000);
  }

}
