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
import model.Trip;
import service.TripService;

import java.io.IOException;

public class StartPageController implements EventHandler {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView tableTrips;



    @FXML
    public void load(){
        System.out.println("jsldjfsf");
        TripService tripService = new TripService();
        Iterable<Trip>  trips = tripService.getAllTrips();
        final ObservableList<Trip> data = FXCollections.observableArrayList();
        for(Trip trip:trips){
            data.add(trip);
        }
        System.out.println(data.toString());

        TableColumn id=new TableColumn("Id");

        TableColumn sightId = new TableColumn("Sight Id");

        TableColumn transportFirma=new TableColumn("Transport Firma");

        TableColumn departureTime = new TableColumn("Departure Time");

        TableColumn price= new TableColumn("Price");

        TableColumn numberOfSeats=new TableColumn("Seats");

        TableColumn information=new TableColumn("Info");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        sightId.setCellValueFactory(new PropertyValueFactory<>("sightId"));
        transportFirma.setCellValueFactory(new PropertyValueFactory<>("transportFirma"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        numberOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        information.setCellValueFactory(new PropertyValueFactory<>("information"));
        tableTrips.setItems(data);
        tableTrips.getColumns().addAll(id, sightId,transportFirma,departureTime,price,numberOfSeats,information);

    }



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
