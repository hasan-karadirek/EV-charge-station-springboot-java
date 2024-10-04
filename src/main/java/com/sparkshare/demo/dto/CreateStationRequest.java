package com.sparkshare.demo.dto;

public class CreateStationRequest {
    private String address;
    private String owner;
    private String name;
    private double latitude;
    private double longitude;


    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getAddress() {
        return address;
    }
    public String getOwner() {
        return owner;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getName() {
        return name;
    }
    

}
