package service;

import model.Trip;
import repository.jdbcRepositoryService.TripJdbcRepository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

public class TripService {

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


    public Iterable<Trip> getAllTrips(){
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        System.out.println(tripJdbcRepository.findAll());
        return tripJdbcRepository.findAll();
    }


    public Iterable<Trip> getAllTripsToSight(int sightId){
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        Iterable<Trip> tripsToSight = tripJdbcRepository.getAllTripsToSight(sightId);
        return tripsToSight;
    }

    public Iterable<Trip> getAllTripsAtDate(Date date){
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        Iterable<Trip> tripsAtDate = tripJdbcRepository.getAllTripsAtDate(date);
        return tripsAtDate;
    }

    public Iterable<Trip> getAllTripsToSightAtDate(int sightId, Date date){
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        Iterable<Trip> tripsAtDate = tripJdbcRepository.getAllTripsToSightAtDate(sightId,date);
        return tripsAtDate;
    }

    public Iterable<Trip> getAllTripssAtDateBetweenHours(int sightId, Date date, int departureHour, int arrivalHour){
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        Iterable<Trip> tripsAtDate = tripJdbcRepository.getAllTripsAtDateBetweenHours( sightId, date, departureHour,arrivalHour);
        return tripsAtDate;
    }

}
