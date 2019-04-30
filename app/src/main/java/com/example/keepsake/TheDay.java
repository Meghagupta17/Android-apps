package com.example.keepsake;

public class TheDay {

    public String leader;
    public String headlines;
    public String milkcost;
    public String diapercost;

    public TheDay(String leader, String headlines, String milkcost, String diapercost) {
        this.leader = leader;
        this.headlines = headlines;
        this.milkcost = milkcost;
        this.diapercost = diapercost;
    }

    public TheDay() {
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getHeadlines() {
        return headlines;
    }

    public void setHeadlines(String headlines) {
        this.headlines = headlines;
    }

    public String getMilkcost() {
        return milkcost;
    }

    public void setMilkcost(String milkcost) {
        this.milkcost = milkcost;
    }

    public String getDiapercost() {
        return diapercost;
    }

    public void setDiapercost(String diapercost) {
        this.diapercost = diapercost;
    }
}
