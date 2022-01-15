package com.example.isa.service.impl.reservations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.isa.dto.PotentialReservationDTO;
import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.ReservationStartEndDateFormatter;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.ReservationSuggestionService;


@Service
public class BoatReservationSuggestionServiceImpl{
	
	@Autowired
	BoatRepository boatRepository;
	@Autowired
	BoatAvailablePeriodRepository periodsRepository;
	@Autowired
	AdditionalServiceRepository additionalServicesRepository;
	
/*
	
	public double calculateReservationPrice(int days, int hours, Boat boat) {
		

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
		
		for(BoatAvailablePeriod p: periodsRepository.findAll()) {
			
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




	@Override
	public List<PotentialReservationDTO> getAvailableEntities(ReservationSearchDTO formParams) throws ParseException {
		String sDate = formParams.getStartDate()+" "+formParams.getStartTime();
		System.out.println(sDate);
				
		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(formParams);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;
		
        List<Boat> boats = FilterByLocationAndAvgGrade(formParams.getLocation(),
        		formParams.getGrade(),getAvailableBoatsBetweenDates(startDate,endDate));
	    return createPotentialReservations(boats,formParams);
	}




	@Override
	public List<PotentialReservationDTO> createPotentialReservations(List<Object> boatss,ReservationSearchDTO formParams){
			

			System.out.println("USLI U PRAVLJENJE RES..");
			List<PotentialReservationDTO> ret = new ArrayList<PotentialReservationDTO>();
			for(Boat b : boats) {
				
				List<String> services = new ArrayList<String>();
				List<Long> servicesId = new ArrayList<Long>();
				
				for(AdditionalService a: additionalServicesRepository.findAllByBoat(b)) {
					
					String serviceInfo = a.getName() + " ( Price per hour: "+a.getPricePerHour()+ ". Price per day: "+a.getPricePerDay()+" ).";
					services.add(serviceInfo);
					System.out.println(serviceInfo);
					servicesId.add(a.getId());
				}
				
				ret.add(new PotentialReservationDTO(b.getId(), b.getName(), b.getPromoDescription(), b.getAvgGrade(), b.getCapacity(),
						b.getCancellationPolicy(), b.getPricePerHour(), b.getPricePerDay(),calculateReservationPrice(formParams.getNumberOfDays(), formParams.getNumberOfHours(),b),
						services, servicesId));
			}
			return ret;
	}




	@Override
	public double calculateReservationPrice(int days, int hours, Object object) {
		double price = 0.00;
		Boat boat = (Boat) object;
		price += days * boat.getPricePerDay();
		price += hours * boat.getPricePerHour();		
		return price;
	}




	@Override
	public List<Object> FilterByLocationAndAvgGrade(String location, float avgGrade, List<Object> list) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Boat> getAvailableEntitiesBetweenDates(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/
}
