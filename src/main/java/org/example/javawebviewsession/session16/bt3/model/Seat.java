package org.example.javawebviewsession.session16.bt3.model;
public class Seat {
    private int id;
    private String nameSeat;
    private double price;
    private int busId;
    private boolean status;

    public Seat() {}

    public Seat(int id, String nameSeat, String busType, int busId, boolean status) {
        this.id = id;
        this.nameSeat = nameSeat;
        this.price = getPriceByType(busType);
        this.busId = busId;
        this.status = status;
    }

    private double getPriceByType(String busType) {
        if (busType.equals("NORMAL")) {
            return 100000;
        } else if (busType.equals("VIP")) {
            return 150000;
        } else if (busType.equals("LUXURY")) {
            return 200000;
        } else {
            return 100000;
        }
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNameSeat(String nameSeat) {
        this.nameSeat = nameSeat;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setBusId(int busId) {
        this.busId = busId;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public String getNameSeat() {
        return nameSeat;
    }
    public double getPrice() {
        return price;
    }
    public int getBusId() {
        return busId;
    }
    public boolean isStatus() {
        return status;
    }
}
