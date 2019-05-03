package com.example.keepsake;

import android.widget.EditText;

public class Arrival {

    public String dobTime;
    public String city;
    public String hospital;
    public String familyfriends;

    public Arrival(String dobTime, String city, String hospital, String familyfriends) {
        this.dobTime = dobTime;
        this.city = city;
        this.hospital = hospital;
        this.familyfriends = familyfriends;
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

    public String getFamilyfriends() {
        return familyfriends;
    }

    public void setFamilyfriends(String familyfriends) {
        this.familyfriends = familyfriends;
    }
}
