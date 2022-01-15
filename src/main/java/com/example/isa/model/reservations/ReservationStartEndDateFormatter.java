package com.example.isa.model.reservations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.isa.dto.ReservationDTO;
import com.example.isa.dto.ReservationSearchDTO;


public class ReservationStartEndDateFormatter  {
	
	private ReservationDTO reservation;
	private ReservationSearchDTO reservationSearch;
	public Date startDate;
	public Date endDate;
	
	
	public ReservationStartEndDateFormatter(ReservationDTO reservation) throws ParseException {
		this.reservation = reservation;
		getStartDate();
		getEndDate();
	}
	
	public ReservationStartEndDateFormatter(ReservationSearchDTO reservationSearch) throws ParseException {
		this.reservationSearch = reservationSearch;
		getSearchStartDate();
		getSearchEndDate();
	}
	
	public void getStartDate()  throws ParseException {		
		if(reservation.startDateTime == null){
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			this.startDate =  formatter.parse(reservation.getStartDate() + " " +reservation.getStartTime());
		} else{
			this.startDate = reservation.startDateTime;
		}

	}
	
	public void getEndDate() {
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, reservation.getNumberOfDays());
        cal.add(Calendar.HOUR, reservation.getNumberOfHours()); 
        this.endDate = cal.getTime();		
	}

	
	
	public void getSearchStartDate() throws ParseException {
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sDate = reservationSearch.getStartDate()+" "+reservationSearch.getStartTime();
		this.startDate=formatter.parse(sDate);	
	}
	
	public void getSearchEndDate() {
		Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, reservationSearch.getNumberOfDays());
        cal.add(Calendar.HOUR, reservationSearch.getNumberOfHours()); 
        this.endDate = cal.getTime();	
		
	}
	
}
