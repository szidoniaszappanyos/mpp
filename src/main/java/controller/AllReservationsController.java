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
import model.Reservation;
import model.Trip;
import service.ReservationService;
import service.TripService;

import java.io.IOException;

public class AllReservationsController implements EventHandler {
    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView tableReservations;

    @FXML
    private Button seeAllButton;

    public void logout(final Event event) {
        setPage();

    }

    @FXML
    private void load(){
        tableReservations.setVisible(true);
        seeAllButton.setVisible(false);

        ReservationService reservationService = new ReservationService();

        TableColumn id = new TableColumn("Id");

        TableColumn number_of_tickets = new TableColumn("Number of Tickets");

        TableColumn phone_number = new TableColumn("Phone Number");

        TableColumn client_name = new TableColumn("Client Name");

        TableColumn trip_id = new TableColumn("Trip Id");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        number_of_tickets.setCellValueFactory(new PropertyValueFactory<>("numberOfTickets"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        client_name.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        trip_id.setCellValueFactory(new PropertyValueFactory<>("tripId"));




            Iterable<Reservation> reservations = reservationService.getAllReservations();
            final ObservableList<Reservation> data = FXCollections.observableArrayList();
            for (Reservation reservation : reservations) {
                data.add(reservation);
            }
            tableReservations.setItems(data);
            tableReservations.getColumns().addAll(id, number_of_tickets, phone_number, client_name, trip_id);


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

}
