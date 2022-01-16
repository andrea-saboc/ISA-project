package com.example.isa.service;

import java.util.List;
import java.util.Set;

import com.example.isa.dto.CustomReservationForClientDto;
import com.example.isa.dto.ReservationDto;
import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.Reservation;
import com.sun.el.parser.ParseException;


public interface ReservationService {
	
	public Reservation createReservation(ReservationDto res) throws ParseException, PeriodNoLongerAvailableException, java.text.ParseException;
	
	public Reservation createReservationForClient(CustomReservationForClientDto dto)  throws PeriodNoLongerAvailableException, ParseException, java.text.ParseException;
	
	public Set<AdditionalService> addAdditionalServices(List<Long> additinalServices);
	
	public double accountAdditionalServices(Set<AdditionalService> additinalServices,ReservationDto res);	
	
	public Reservation cancelReservation(long resId);
	
	public List<Reservation> GetReservationHistory();
	
	
}
