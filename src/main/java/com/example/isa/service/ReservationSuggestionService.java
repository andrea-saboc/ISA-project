package com.example.isa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.isa.dto.PotentialReservationDto;
import com.example.isa.dto.ReservationSearchDto;
import com.example.isa.model.Boat;


public interface ReservationSuggestionService {
	
	
	
	public List<PotentialReservationDto> getAvailableEntities(ReservationSearchDto formParams) throws ParseException;
	
	public List<PotentialReservationDto> createPotentialReservations(List<Object> list,ReservationSearchDto formParams);
	
	public double calculateReservationPrice(int days, int hours, Object object);
	
	public List<Object> FilterByLocationAndAvgGrade(String location, float avgGrade,List<Object> list);
	
	public List<Boat> getAvailableEntitiesBetweenDates(Date startDate, Date endDate);
	
	
}
