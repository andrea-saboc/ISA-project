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

import com.example.isa.dto.PotentialReservationDto;
import com.example.isa.dto.ReservationSearchDto;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.MansionAvailablePeriod;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.ReservationStartEndDateFormatter;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.MansionAvailablePeriodRepository;
import com.example.isa.repository.MansionRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.ReservationSuggestionService;

@Service
@Transactional(readOnly=true)
public class MansionReservationSuggestionServiceImpl implements ReservationSuggestionService{

	@Autowired 
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	MansionRepository mansionRepo;
	@Autowired
	MansionAvailablePeriodRepository availablePeriodsRepo;
	@Autowired
	AdditionalServiceRepository additinalServicesRepo;
	
	@Override
	public List<PotentialReservationDto> getAvailableEntities(ReservationSearchDto formParams) throws ParseException {

		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(formParams);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;
		
        List<Mansion> mansions = FilterByLocationAndAvgGrade(
        		formParams.getLocation(),formParams.getGrade(), getAvailableMansionsBetweenDates(startDate,endDate));
	    return createPotentialReservations(mansions,formParams);
	}
	
		
		public List<PotentialReservationDto> createPotentialReservations(List<Mansion> mansions,ReservationSearchDto formParams){

			List<PotentialReservationDto> ret = new ArrayList<PotentialReservationDto>();
			for(Mansion m : mansions) {
				
				List<String> services = new ArrayList<String>();
				List<Long> servicesId = new ArrayList<Long>();
				
				for(AdditionalService a: additinalServicesRepo.findAllByMansion(m)) {
					
					String serviceInfo = a.getName() + " ( Price per day: "+a.getPricePerDay()+" ).";
					services.add(serviceInfo);
					servicesId.add(a.getId());
				}
				
				ret.add(new PotentialReservationDto(m.getId(), m.getName(), m.getPromoDescription(), m.getAvgGrade(),
						m.getPricePerDay(), m.getPriceForSevenDays(), formParams.getPrice(m),services,servicesId));
			}
			return ret;
		}
	
		
	public List<Mansion> FilterByLocationAndAvgGrade(String location, float avgGrade,List<Mansion> boats){
		
		List<Mansion> ret = new ArrayList<Mansion>();
		for(Mansion b : boats) {
			if((b.getAddress().getAddress().contains(location) || b.getAddress().getCity().contains(location)) && b.getAvgGrade()>= avgGrade)
				ret.add(b);
			
		}
		
		return ret;
		
	}
	public List<Mansion> getAvailableMansionsBetweenDates(Date startDate, Date endDate){
		
		List<Mansion> ret = new ArrayList<Mansion>();
		
		for(MansionAvailablePeriod p: availablePeriodsRepo.findAll()) {
			
			if(isDateInBetweenIncludingEndPoints(p.getStartDate(),p.getEndDate(),startDate) &&
					isDateInBetweenIncludingEndPoints(p.getStartDate(),p.getEndDate(),endDate) &&
					!ret.contains(p.getMansion()))
				
				ret.add(p.getMansion());
		}
		return ret;
	}
	
	public static boolean isDateInBetweenIncludingEndPoints(final Date min, final Date max, final Date date){
	    return !(date.before(min) || date.after(max));
	}




	
	
}
