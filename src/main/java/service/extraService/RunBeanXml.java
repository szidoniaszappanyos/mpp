package service.extraService;

import model.Sight;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunBeanXml {
    public static void main(String[] args) {
        //Pizza Bean with dependecies
        ApplicationContext pizzafactory = new
                ClassPathXmlApplicationContext("classpath:spring-pizza.xml");
        Pizza pizza= pizzafactory.getBean(Pizza.class);
        System.out.println("___________________________" + pizza.toString());

        //Sight bean
        ApplicationContext sightfactory = new
                ClassPathXmlApplicationContext("classpath:spring-sight.xml");
        Sight sight= sightfactory.getBean(Sight.class);
        System.out.println("___________________________" + sight.toString());

    }
}
