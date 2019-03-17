package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class StartPageController implements EventHandler {

    @FXML
    private MenuBar menuBar;

    public void logout(final Event event){
        setPage();
    }

    @Override
    public void handle(final Event event) {
        if(event.getSource() instanceof MenuItem){
            logout(event);
        }

    }

    private void setPage() {
        Stage primaryStage = (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Pane myPane2 = null;
        try {
            myPane2 = (Pane) loader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(myPane2));
    }
}
