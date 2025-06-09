package org.example.javawebviewsession.session16.bt3.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Bus {
    private int id;

    @NotBlank(message = "Biển số không được trống")
    private String licensePlate;

    @NotBlank(message = "Loại xe không được trống")
    private String busType;

    @Min(value = 1, message = "Số hàng ghế phải lớn hơn 0")
    private int rowSeat;

    @Min(value = 1, message = "Số cột ghế phải lớn hơn 0")
    private int colSeat;

    private int totalSeat;

    @NotBlank(message = "Hình ảnh không được trống")
    private String image;

    public Bus(int id, String licensePlate, String busType, int rowSeat, int colSeat,  int totalSeat, String image) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.busType = busType;
        this.rowSeat = rowSeat;
        this.colSeat = colSeat;
        this.totalSeat = totalSeat;
        this.image = image;
    }

    public Bus () {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public String getBusType() {
        return busType;
    }
    public void setBusType(String busType) {
        this.busType = busType;
    }
    public int getRowSeat() {
        return rowSeat;
    }
    public void setRowSeat(int rowSeat) {
        this.rowSeat = rowSeat;
    }
    public int getColSeat() {
        return colSeat;
    }
    public void setColSeat(int colSeat) {
        this.colSeat = colSeat;
    }
    public int getTotalSeat() {
        return totalSeat;
    }
    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
