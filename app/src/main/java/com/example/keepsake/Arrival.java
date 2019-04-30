package com.example.keepsake;

public class Arrival {

    public String dobTime;
    public String city;
    public String hospital;
    public String familypc;

    public Arrival(String dobTime, String city, String hospital, String familypc) {
        this.dobTime = dobTime;
        this.city = city;
        this.hospital = hospital;
        this.familypc = familypc;
    }

    public Arrival() {
    }

    public String getDobTime() {
        return dobTime;
    }

    public void setDobTime(String dobTime) {
        this.dobTime = dobTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getFamilypc() {
        return familypc;
    }

    public void setFamilypc(String familypc) {
        this.familypc = familypc;
    }
}
