package model;

import repository.HasID;

public class Reservation implements HasID<Integer> {
    private Integer id;
    private int numberOfTickets;
    private int phoneNumber;
    private String clientName;
    private int tripId;

    public Reservation(Integer id, int numberOfTickets, int phoneNumber, String clientName, int tripId) {
        this.id = id;
        this.numberOfTickets = numberOfTickets;
        this.phoneNumber = phoneNumber;
        this.clientName = clientName;
        this.tripId = tripId;
    }

    public Reservation() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", numberOfTickets=" + numberOfTickets +
                ", phoneNumber=" + phoneNumber +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
