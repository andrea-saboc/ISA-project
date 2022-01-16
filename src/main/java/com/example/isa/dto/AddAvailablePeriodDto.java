package com.example.isa.dto;

import java.util.Date;

public class AddAvailablePeriodDto {
    public Date startTime;
    public Date endTime;
    public Long boatId;

    public  AddAvailablePeriodDto(){}

    public AddAvailablePeriodDto(Date startTime, Date endTime, Long boatId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.boatId = boatId;
    }
}
