import model.Sight;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.LogTest;
import service.Pizza;

public class StartApplication {
    public static void main(String[] args) {
        System.out.println("hello");
        LogTest log = new LogTest();
        System.out.println(log.retrieveMessage());

        ApplicationContext pizzafactory = new
                ClassPathXmlApplicationContext("classpath:spring-pizza.xml");
        Pizza pizza= pizzafactory.getBean(Pizza.class);
        System.out.println("___________________________" + pizza.toString());

        ApplicationContext sightfactory = new
                ClassPathXmlApplicationContext("classpath:spring-sight.xml");
        Sight sight= sightfactory.getBean(Sight.class);
        System.out.println("___________________________" +sight.toString());

    }
}
