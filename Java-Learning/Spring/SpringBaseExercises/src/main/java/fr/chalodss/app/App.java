package fr.chalodss.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fr.chalodss.classes.Bear;
import fr.chalodss.classes.Food;


public class App {

  @Bean(name = "Bart")
  public Bear createBear() {
    return new Bear();
  }

  public static void main(String[] args) {
    Bear b1 = new Bear();

    b1.eat(Food.MEAT);

    try (var ctx = new ClassPathXmlApplicationContext("/beans.xml", App.class)) {
      Bear b2 = ctx.getBean("bear", Bear.class);

      b2.eat(Food.FRUITS);
    }

    try (var ctx = new AnnotationConfigApplicationContext(App.class)) {
      Bear b3 = ctx.getBean("Bart", Bear.class);

      b3.eat(Food.VEGETABLES);
    }
  }

}
