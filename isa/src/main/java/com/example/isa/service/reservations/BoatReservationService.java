package com.example.isa.service.reservations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.isa.model.*;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.isa.dto.ReservationDTO;

@Service
@Transactional(readOnly=true)
public class BoatReservationService {

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatAvailablePeriodRepository availablePeriodsRepo;
	@Autowired
	AdditionalServiceRepository additinalServicesRepo;
	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	
	@Transactional(readOnly = false)
	public BoatReservation createBoatReservation(ReservationDTO res) {
		
		String sDate = res.getStartDate()+" "+res.getStartTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			Date startDate=formatter.parse(sDate);			
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(startDate);
	        cal.add(Calendar.DAY_OF_MONTH, res.getNumberOfDays());
	        cal.add(Calendar.HOUR, res.getNumberOfHours()); 
	        Date endDate = cal.getTime();
	                
	        System.out.println("Adding days to start date: "+endDate);
	        
	        BoatReservation newBoatReservation = new BoatReservation(getLoggedUser(), startDate,endDate, res.getNumberOfGuests(), res.getPrice(),
					boatRepo.findById(res.getEntityId()).orElse(new Boat()));
	        
	        
	        //VELIKI PROBLEM, POSMATRAS KAO DA POSTOJI SAMO JEDAN BROD,AKO VISE BRODOVA IMA PREKLAPAJUCE TERMINE OVO VRATI VISE OD JEDNOG REZ!!
	        BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, startDate);
	         
	        if(!period.getStartDate().equals(startDate)) {
	        	BoatAvailablePeriod periodBefore = new BoatAvailablePeriod(period.getStartDate(),startDate,period.getBoat());
	        	availablePeriodsRepo.save(periodBefore);
	        }
	        if(!period.getEndDate().equals(endDate)) {
	        	BoatAvailablePeriod periodAfter = new BoatAvailablePeriod(endDate,period.getEndDate(),period.getBoat());
	        	availablePeriodsRepo.save(periodAfter);
	        }
	        
	        Set<AdditionalService> services = new HashSet<AdditionalService>();
	        for(long id : res.getAdditionalServices()) {
	        	AdditionalService service = additinalServicesRepo.findById(id).orElse(null);
	        	services.add(service);
	        	newBoatReservation.setTotalPrice( newBoatReservation.getTotalPrice()
	        			+calculateAdditionalServicesPrice(newBoatReservation,res,service));
	        }
	        newBoatReservation.setAdditionalServices(services);
	        
	        
	        availablePeriodsRepo.delete(period);
		    return boatReservationRepo.save(newBoatReservation);
		    
			} catch (ParseException e) {
			System.out.println("PUČE!");
			e.printStackTrace();
			}
				
		return null;
	}
	
	public double calculateAdditionalServicesPrice(BoatReservation bres,ReservationDTO res,AdditionalService service) {
		
		double initialPrice = bres.getTotalPrice();
		initialPrice += service.getPricePerDay() * res.getNumberOfDays();
		initialPrice += service.getPricePerHour() * res.getNumberOfHours();
		return initialPrice;
	}

	
	public User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@Transactional(readOnly = false)
	public BoatReservation cancelBoatReservation(long resId) {
		
		BoatReservation res = boatReservationRepo.findById(resId);

		BoatAvailablePeriod periodBefore = availablePeriodsRepo.checkIfPeriodHasEndDate(res.getStartDate());
		BoatAvailablePeriod periodAfter = availablePeriodsRepo.checkIfPeriodHasStartDate(res.getEndDate());		
		BoatAvailablePeriod periodToAdd;
		
		if(periodBefore!=null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new BoatAvailablePeriod(periodBefore.getStartDate(),periodAfter.getEndDate(),res.getBoat());	
		}
		else if (periodBefore==null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new BoatAvailablePeriod(res.getStartDate(),periodAfter.getEndDate(),res.getBoat());
		}
		else if (periodBefore!=null && periodAfter==null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			periodToAdd = new BoatAvailablePeriod(periodBefore.getStartDate(),res.getEndDate(),res.getBoat());
		}
		else 
			periodToAdd = new BoatAvailablePeriod(res.getStartDate(),res.getEndDate(),res.getBoat());
		
		
		availablePeriodsRepo.save(periodToAdd);
		BoatReservation b = boatReservationRepo.findById(resId);
		b.setCancelled(true);
		boatReservationRepo.save(b);
		return null;
	}
	
	public List<BoatReservation> GetBoatReservationHistory(){
		
		Date today = new Date();
		List<BoatReservation> res = new ArrayList<BoatReservation>();
		for(BoatReservation m: boatReservationRepo.findAllByUser(getLoggedUser())) {
			if(m.getEndDate().before(today) && !m.isCancelled())
				res.add(m);
		}
		System.out.println("KOLIKO IMA BOATS "+res.size());
		return res;
		
		//return boatReservationRepo.findAllByUser(getLoggedUser());

	}

    public List<BoatReservation> getLoggedUserReservations() {
		User user = getLoggedUser();
		BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
		List<Boat> ownersBoats = boatRepo.findBoatByBoatOwner(boatOwner);
		List<BoatReservation> boatReservations = new ArrayList<>();
		for ( Boat boat : ownersBoats) {
			boatReservations.addAll(boatReservationRepo.findAllByBoat(boat));
		}
		return boatReservations;
    }
}
