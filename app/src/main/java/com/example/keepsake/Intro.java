package com.example.keepsake;

public class Intro {

    public String name;
    public String dob;
    public String weight;
    public String height;

    public Intro(String name, String dob, String weight, String height) {
        this.name = name;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    public Intro() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
