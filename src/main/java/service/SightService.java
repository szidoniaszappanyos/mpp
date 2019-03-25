package service;

import model.Sight;
import model.Trip;
import repository.jdbcRepositoryService.SightJdbcRepository;
import repository.jdbcRepositoryService.TripJdbcRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SightService {
    private SightJdbcRepository initSightJdbcRepository(){
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        SightJdbcRepository sightJdbcRepository = new SightJdbcRepository(serverProps);
        return sightJdbcRepository;
    }


    public Iterable<Sight> getAllSights(){
        SightJdbcRepository sightJdbcRepository = initSightJdbcRepository();
        System.out.println(sightJdbcRepository.findAll());
        return sightJdbcRepository.findAll();
    }
}
