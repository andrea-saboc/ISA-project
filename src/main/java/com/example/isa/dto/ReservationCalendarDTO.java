package com.example.isa.dto;

import java.util.Date;

public class ReservationCalendarDTO {

    public Date start;
   public  Date end;
   public String title;
   public String description;

    public ReservationCalendarDTO() {

    }
    public ReservationCalendarDTO(Date start, Date end, String title,String description) {

        super();
        this.end=end;
        this.start=start;
        this.title=title;
        this.description=description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
