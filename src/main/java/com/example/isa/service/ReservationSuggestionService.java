package com.example.isa.service;

import java.text.ParseException;
import java.util.List;

import com.example.isa.dto.PotentialReservationDto;
import com.example.isa.dto.ReservationSearchDto;



public interface ReservationSuggestionService {
	
		
	public List<PotentialReservationDto> getAvailableEntities(ReservationSearchDto formParams) throws ParseException;

		
}
