import model.Reservation;
import model.Sight;
import model.Trip;
import org.junit.Assert;
import org.junit.Test;
import repository.repositoryService.ReservationJdbcRepository;
import repository.repositoryService.SightJdbcRepository;
import repository.repositoryService.TripJdbcRepository;
import uk.co.jemos.podam.api.*;

import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

public class ReservationTest {
    public Properties getServerProperties() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        return serverProps;
    }

    public ReservationJdbcRepository initReservationJdbcRepository() {
        Properties serverProps = getServerProperties();
        ReservationJdbcRepository reservationJdbcRepository = new ReservationJdbcRepository(serverProps);
        return reservationJdbcRepository;
    }

    public TripJdbcRepository initTripJdbcRepository() {
        Properties serverProps = getServerProperties();
        TripJdbcRepository tripJdbcRepository = new TripJdbcRepository(serverProps);
        return tripJdbcRepository;
    }


    public SightJdbcRepository initSightJdbcRepository() {
        Properties serverProps = getServerProperties();
        SightJdbcRepository sightJdbcRepository = new SightJdbcRepository(serverProps);
        return sightJdbcRepository;
    }

    @Test
    public void reservationTest() {
        PodamFactory factory = new PodamFactoryImpl();
        Reservation reservation1 = factory.manufacturePojo(Reservation.class);
        System.out.println(reservation1.toString());
    }

    @Test
    public void sizeAndSaveTest() {
        ReservationJdbcRepository reservationJdbcRepository = initReservationJdbcRepository();
        TripJdbcRepository tripJdbcRepository = initTripJdbcRepository();
        SightJdbcRepository sightJdbcRepository = initSightJdbcRepository();

        PodamFactory factory = new PodamFactoryImpl();

        Sight sight = factory.manufacturePojo(Sight.class);
        sightJdbcRepository.save(sight);

        DefaultClassInfoStrategy classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        classInfoStrategy.addExcludedField(Trip.class, "departureTime");
        factory.setClassStrategy(classInfoStrategy);
        Trip trip = factory.manufacturePojo(Trip.class);
        trip.setSightId(sight.getId());
        tripJdbcRepository.save(trip);

        Reservation reservation = factory.manufacturePojo(Reservation.class);
        reservation.setTripId(trip.getId());
        int sizeBefore = reservationJdbcRepository.size();
        System.out.println(reservationJdbcRepository.size());
        reservationJdbcRepository.save(reservation);
        int sizeAfter = reservationJdbcRepository.size();
        System.out.println(reservationJdbcRepository.size());

        Assert.assertNotEquals(sizeBefore, sizeAfter);
    }
}
