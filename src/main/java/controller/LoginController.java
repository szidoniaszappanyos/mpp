package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.UserService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements EventHandler {
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField username;

    // The reference of outputText will be injected by the FXML loader
    @FXML
    private PasswordField password;

    @FXML
    private Button login;


    public LoginController() {
    }

    @FXML
    private void initialize() {
    }

    @Override
    public void handle(final Event event) {
        String message;
        UserService userService = new UserService();
        message = userService.login(username.getText(), password.getText());
        if (message == "Successful login") {
            setStartPage(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(message);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }

    private void setStartPage(final Event event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../view/startPage.fxml"));
        Pane myPane2 = null;
        try {
            myPane2 = (Pane) loader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(myPane2));
    }

}
