package com.example.isa.dto;

import java.util.Date;

public class AddAvailablePeriodFishingInstructorDto {

    public Date startTime;
    public Date endTime;
    public Long fishingId;

    public  AddAvailablePeriodFishingInstructorDto(){}

    public AddAvailablePeriodFishingInstructorDto(Date startTime, Date endTime, Long fishingId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fishingId = fishingId;
    }
}
