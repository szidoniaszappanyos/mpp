package service;

import model.Trip;
import repository.jdbcRepositoryService.TripJdbcRepository;

import java.io.FileReader;
import java.io.IOException;
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
}
