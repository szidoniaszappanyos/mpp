package service;

import model.Reservation;
import repository.jdbcRepositoryService.ReservationJdbcRepository;
import repository.jdbcRepositoryService.TripJdbcRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReservationService {
    private ReservationJdbcRepository initReservationJdbcRepository() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        ReservationJdbcRepository reservationJdbcRepository = new ReservationJdbcRepository(serverProps);
        return reservationJdbcRepository;
    }



    private TripJdbcRepository initTripJdbcRepository(){
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        TripJdbcRepository tripJdbcRepository = new TripJdbcRepository(serverProps);
        return tripJdbcRepository;
    }

    public Iterable<Reservation> getAllReservations() {
        ReservationJdbcRepository reservationJdbcRepository = initReservationJdbcRepository();
        System.out.println(reservationJdbcRepository.findAll());
        return reservationJdbcRepository.findAll();
    }

    public String checkIfReservationAdded(int sizeBefore, int id, int seats) {
        ReservationJdbcRepository reservationJdbcRepository = initReservationJdbcRepository();
        if (reservationJdbcRepository.size() != sizeBefore) {
            updateTrip(id, seats);
            return "Successfully added";
        } else
            return "Unsuccessfully added";
    }

    public String addReservation(int tripId, String name, int phone, int seats) {
        int id = (int) (Math.random() * 50 + 1);
        ReservationJdbcRepository reservationJdbcRepository = initReservationJdbcRepository();
        int sizeBefore = reservationJdbcRepository.size();
        reservationJdbcRepository.save(new Reservation(id, seats, phone, name, tripId));
        return checkIfReservationAdded(sizeBefore, tripId, seats);
    }

    public void updateTrip(int tripId, int seats) {
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        tripJdbcRepository.updateSeats(tripId, seats);
    }


}
