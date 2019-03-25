package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Sight;
import model.Trip;
import service.ReservationService;
import service.SightService;
import service.TripService;

import java.io.IOException;

public class ReservationController implements EventHandler {

    @FXML
    private Button reserveButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private ChoiceBox tripBox;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField seats;


    public void logout(final Event event) {
        setPage();
    }

    @FXML
    public void reserve() {
        String trip = tripBox.getValue().toString();
        String tripIdString = trip.substring(8).split(",")[0];
        int tripIdInt = Integer.parseInt(tripIdString);
        ReservationService reservationService = new ReservationService();
        String message = reservationService.addReservation(tripIdInt, name.getText(), Integer.parseInt(phone.getText()), Integer.parseInt(seats.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });

    }

    @FXML
    public void initTrips() {
        loadTrips();
    }

    @Override
    public void handle(final Event event) {
        if (event.getSource() instanceof MenuItem) {
            if (((MenuItem) event.getSource()).getText().equals("Logout")) {
                logout(event);
            }
            if (((MenuItem) event.getSource()).getText().equals("See Trips")) {
                setStartPage();
            }
            if (((MenuItem) event.getSource()).getText().equals("See Reservations")) {
                setAllReservationsPage();
            }

        }
    }

    @FXML
    public void loadTrips() {
        TripService tripService = new TripService();
        Iterable<Trip> trips = tripService.getAllTrips();
        final ObservableList<Trip> data = FXCollections.observableArrayList();
        for (Trip trip : trips) {
            data.add(trip);
        }
        tripBox.setItems(data);
        tripBox.getItems().addAll();

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

    private void setStartPage() {
        Stage primaryStage = (Stage) (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../view/startPage.fxml"));
        Pane myPane2 = null;
        try {
            myPane2 = (Pane) loader2.load();
            StartPageController ctrl = loader2.getController();
            ctrl.load();
            ctrl.loadSights();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(new Scene(myPane2));
    }

    private void setAllReservationsPage() {
        Stage primaryStage = (Stage) (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../view/allReservations.fxml"));
        Pane myPane2 = null;
        try {
            myPane2 = (Pane) loader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(new Scene(myPane2));
    }

}
