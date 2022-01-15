package com.example.isa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.isa.dto.PotentialReservationDTO;
import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.model.Boat;


public interface ReservationSuggestionService {
	
	
	
	public List<PotentialReservationDTO> getAvailableEntities(ReservationSearchDTO formParams) throws ParseException;
	
	public List<PotentialReservationDTO> createPotentialReservations(List<Object> list,ReservationSearchDTO formParams);
	
	public double calculateReservationPrice(int days, int hours, Object object);
	
	public List<Object> FilterByLocationAndAvgGrade(String location, float avgGrade,List<Object> list);
	
	public List<Boat> getAvailableEntitiesBetweenDates(Date startDate, Date endDate);
	
	
}
