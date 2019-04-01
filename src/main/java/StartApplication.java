import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Sight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.extraService.Bag;
import service.extraService.Box;
import service.extraService.Lasagna;
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



    }
}
