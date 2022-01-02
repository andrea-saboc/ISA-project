package com.example.isa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.model.AvailablePeriod;
import com.example.isa.model.Boat;
import com.example.isa.repository.AvailablePeriodRepository;
import com.example.isa.repository.BoatRepository;


@Service
public class ReservationSuggestionService {
	
	@Autowired
	BoatRepository boatRepository;
	@Autowired
	AvailablePeriodRepository periodsRepository;

	public List<Boat> getAvailableBoats(ReservationSearchDTO formParams){
			
	String sDate = formParams.getStartDate()+" "+formParams.getStartTime();
	System.out.println(sDate);
	
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try {
		Date startDate=formatter.parse(sDate);
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
 
        // add 1 days to current day
        cal.add(Calendar.DAY_OF_MONTH, formParams.getNumberOfDays()); 
        Date endDate = cal.getTime();
        System.out.println("Adding days to start date: "+endDate);
	    return getAvailableBoatsBetweenDates(startDate,endDate);
	    
		} catch (ParseException e) {
		System.out.println("PUČE!");
		e.printStackTrace();
		}
			
	return null;
	}
	
	public List<Boat> FilterByLocationAndAvgGrade(String location, float avgGrade,List<Boat> boats){
		
		List<Boat> ret = new ArrayList<Boat>();
		for(Boat b : boats) {
			if((b.getAddress().getAddress().contains(location) || b.getAddress().getCity().contains(location)) && b.getAvgGrade()>= avgGrade)
				ret.add(b);
			
		}
		
		return ret;
		
	}
	public List<Boat> getAvailableBoatsBetweenDates(Date startDate, Date endDate){
		
		List<Boat> ret = new ArrayList<Boat>();
		
		for(AvailablePeriod p: periodsRepository.findAll()) {
			
			if(isDateInBetweenIncludingEndPoints(p.getStartDate(),p.getEndDate(),startDate) &&
					isDateInBetweenIncludingEndPoints(p.getStartDate(),p.getEndDate(),endDate) &&
					!ret.contains(p.getBoat()))
				
				ret.add(p.getBoat());
		}
		return ret;
	}
	
	public static boolean isDateInBetweenIncludingEndPoints(final Date min, final Date max, final Date date){
	    return !(date.before(min) || date.after(max));
	}
}
