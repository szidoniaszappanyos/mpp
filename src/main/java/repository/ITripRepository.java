package repository;

import model.Trip;

import java.sql.Timestamp;
import java.sql.Date;

public interface ITripRepository  extends IRepository<Integer, Trip> {
  Iterable<Trip> getAllTripsToSight(int sightId);
  Iterable<Trip> getAllTripsAtDate(Date date);
  Iterable<Trip> getAllTripsToSightAtDate(int sightId, Date date);
  Iterable<Trip> getAllTripsAtDateBetweenHours(int sightId, Date date, int departureHour, int arrivalHour);
  void updateSeats(int tripId, int seats);
}
