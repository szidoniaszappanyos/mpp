package model;

import repository.HasID;

import java.sql.Timestamp;
import java.util.Objects;

public class Trip implements HasID<Integer> {
    private int id;
    private int sightId;
    private String transportFirma;
    private Timestamp departureTime;
    private Float price;
    private int numberOfSeats;
    private String information;

    public Trip(int id, int sightId, String transportFirma, Timestamp departureTime, Float price, int numberOfSeats, String information) {
        this.id = id;
        this.sightId = sightId;
        this.transportFirma = transportFirma;
        this.departureTime = departureTime;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.information = information;
    }

    public Trip() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getSightId() {
        return sightId;
    }

    public void setSightId(int sightId) {
        this.sightId = sightId;
    }

    public String getTransportFirma() {
        return transportFirma;
    }

    public void setTransportFirma(String transportFirma) {
        this.transportFirma = transportFirma;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                numberOfSeats == trip.numberOfSeats &&
                Objects.equals(transportFirma, trip.transportFirma) &&
                Objects.equals(departureTime, trip.departureTime) &&
                Objects.equals(price, trip.price) &&
                Objects.equals(information, trip.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transportFirma, departureTime, price, numberOfSeats, information);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", transportFirma='" + transportFirma + '\'' +
                ", departureTime=" + departureTime +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                ", information='" + information + '\'' +
                '}';
    }
}
