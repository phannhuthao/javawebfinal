package org.example.javawebviewsession.session16.bt2.model;

public class CarTrip {
    private String id;
    private String carName;
    // điểm đến
    private String destination;
    // điểm đi
    private String departure;

    public CarTrip(String id, String carName, String destination, String departure) {
        this.id = id;
        this.carName = carName;
        this.destination = destination;
        this.departure = departure;
    }
    public CarTrip() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCarName() {
        return carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
