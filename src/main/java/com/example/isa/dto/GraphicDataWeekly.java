package com.example.isa.dto;

public class GraphicDataWeekly {
    private Integer Monday = 0;
    private Integer Tuesday = 0;
    private Integer Wednesday =0;
    private Integer Thurstady = 0;
    private Integer Friday = 0;
    private Integer Saturday = 0;
    private Integer Sunday = 0;
    private double incomings = 0;

    public double getIncomings() {
        return incomings;
    }

    public void setIncomings(double incomings) {
        this.incomings = incomings;
    }

    public Integer getMonday() {
        return Monday;
    }

    public void setMonday(Integer monday) {
        Monday = monday;
    }

    public Integer getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Integer tuesday) {
        Tuesday = tuesday;
    }

    public Integer getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Integer wednesday) {
        Wednesday = wednesday;
    }

    public Integer getThurstady() {
        return Thurstady;
    }

    public void setThurstady(Integer thurstady) {
        Thurstady = thurstady;
    }

    public Integer getFriday() {
        return Friday;
    }

    public void setFriday(Integer friday) {
        Friday = friday;
    }

    public Integer getSaturday() {
        return Saturday;
    }

    public void setSaturday(Integer saturday) {
        Saturday = saturday;
    }

    public Integer getSunday() {
        return Sunday;
    }

    public void setSunday(Integer sunday) {
        Sunday = sunday;
    }
}
