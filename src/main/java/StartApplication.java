import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Sight;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.extraService.Pizza;

import java.io.IOException;

public class StartApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Login");

        FXMLLoader loader=new FXMLLoader(getClass().getResource("view/login.fxml"));
        LoginController ctrl=loader.getController();
        Pane myPane = (Pane) loader.load();
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();
    }


    public static void main(String[] args) {
        //Java Fx launch
        launch(args);


      /*  System.out.println("hello");
        LogTest log = new LogTest();
        System.out.println(log.retrieveMessage());*/

        //Pizza Bean with dependecies
        ApplicationContext pizzafactory = new
                ClassPathXmlApplicationContext("classpath:spring-pizza.xml");
        Pizza pizza= pizzafactory.getBean(Pizza.class);
        System.out.println("___________________________" + pizza.toString());

        //Sight bean
        ApplicationContext sightfactory = new
                ClassPathXmlApplicationContext("classpath:spring-sight.xml");
        Sight sight= sightfactory.getBean(Sight.class);
        System.out.println("___________________________" +sight.toString());

    }
}
