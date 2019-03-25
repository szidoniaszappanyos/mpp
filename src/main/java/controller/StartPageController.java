package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Sight;
import model.Trip;
import org.apache.logging.log4j.core.util.KeyValuePair;
import service.SightService;
import service.TripService;

import java.io.IOException;

public class StartPageController implements EventHandler {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView tableTrips;

    @FXML
    private ChoiceBox sightBox;

    @FXML
    public void load() {
        TripService tripService = new TripService();

        TableColumn id = new TableColumn("Id");

        TableColumn sightId = new TableColumn("Sight Id");

        TableColumn transportFirma = new TableColumn("Transport Firma");

        TableColumn departureTime = new TableColumn("Departure Time");

        TableColumn price = new TableColumn("Price");

        TableColumn numberOfSeats = new TableColumn("Seats");

        TableColumn information = new TableColumn("Info");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        sightId.setCellValueFactory(new PropertyValueFactory<>("sightId"));
        transportFirma.setCellValueFactory(new PropertyValueFactory<>("transportFirma"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        numberOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        information.setCellValueFactory(new PropertyValueFactory<>("information"));


        if (sightBox.getValue() != null) {
            String sight = sightBox.getValue().toString();
            String sightIdString = sight.substring(9).split(",")[0];
            int sightIdInt = Integer.parseInt(sightIdString);
            Iterable<Trip> trips = tripService.getAllTripsToSight(sightIdInt);
            final ObservableList<Trip> data = FXCollections.observableArrayList();
            for (Trip trip : trips) {
                data.add(trip);
            }
            tableTrips.setItems(data);
            tableTrips.getColumns().addAll(id, sightId, transportFirma, departureTime, price, numberOfSeats, information);


        } else {
            Iterable<Trip> trips = tripService.getAllTrips();
            final ObservableList<Trip> data = FXCollections.observableArrayList();
            for (Trip trip : trips) {
                data.add(trip);
            }
            tableTrips.setItems(data);
            tableTrips.getColumns().addAll(id, sightId, transportFirma, departureTime, price, numberOfSeats, information);

        }
    }

    @FXML
    public void loadSights() {
        SightService sightService = new SightService();
        Iterable<Sight> sights = sightService.getAllSights();
        final ObservableList<Sight> data = FXCollections.observableArrayList();
        for (Sight sight : sights) {
            data.add(sight);
        }
        sightBox.setItems(data);
        sightBox.getItems().addAll();

    }


    public void logout(final Event event) {
        setPage();
    }

    public void reserve(final Event event) {
        setReservePage();
    }

    @Override
    public void handle(final Event event) {
        if (event.getSource() instanceof MenuItem) {
            if (((MenuItem) event.getSource()).getText().equals("Logout")) {
                logout(event);
            }

            if (((MenuItem) event.getSource()).getText().equals("Reserve sights")) {
                reserve(event);
            }
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

    private void setReservePage() {
        Stage primaryStage = (Stage) menuBar.getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../view/reservationPage.fxml"));
        ReservationController ctrl = loader2.getController();
        Pane myPane2 = null;
        try {
            myPane2 = (Pane) loader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(myPane2));
    }
}
