package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.isa.dto.ReservationCalendarDTO;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.service.impl.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.service.AuthenticationService;

@Service
public class CollectingBoatReservationsServiceImpl {
	
	
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	BoatReservationRepository boatReservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatOwnerRepository boatOwnerRepo;
	@Autowired
	BoatDiscountReservationRepository boatDiscountReservationRepo;
	@Autowired
	BoatService boatService;

	public List<BoatReservation> GetReservationHistory() {
		
		List<BoatReservation> ret = new ArrayList<BoatReservation>();
		ret.addAll(GetDiscountReservationHistory());
		ret.addAll(boatReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.CLOSED));
		return ret;
	}
	
	public List<BoatReservation> GetDiscountReservationHistory(){
		
		List<BoatReservation> ret = new ArrayList<BoatReservation>();
		for(BoatDiscountReservation r: 
			boatDiscountReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(), ReservationStatus.CLOSED)) {
			
			ret.add(new BoatReservation(r.getUser(), r.getStartDate(),r.getEndDate(),r.getNumberOfGuests(), r.getPriceWithDiscount(),
			r.getBoat()));
		}
		return ret;
	}

    public List<BoatReservation> getOwnerReservations() {

		BoatOwner boatOwner = boatOwnerRepo.findById(authenticationService.getLoggedUser().getId()).get();		
		List<Boat> ownersBoats = boatRepo.findBoatByBoatOwner(boatOwner);
		
		
		List<BoatReservation> boatReservations = new ArrayList<>();
		for ( Boat boat : ownersBoats) {
			boatReservations.addAll(boatReservationRepo.findAllByBoat(boat));
		}
		return boatReservations;
    }

	public List<AbstractReservation> getOwnersNotCanceledReservations(User owner){
		List<AbstractReservation> allReservations = new ArrayList<>();
		List<Boat> ownersBoat = boatService.getByOwner((BoatOwner) owner);
		for (Boat boat : ownersBoat){
			allReservations.addAll(boatReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED));
			allReservations.addAll(boatDiscountReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED));
		}
		return allReservations;
	}


	public List<BoatReservation> getBoatReservationsByBoat(Long boatId) {
		Boat boat = boatRepo.findById(boatId).get();
		List<BoatReservation> boatReservations = boatReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED);
		return boatReservations;
	}

	public List<AbstractReservation> getAllNotCancelledReservationsByBoat(Boat boat){
		List<AbstractReservation> allMansionReservations = new ArrayList<>(boatReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED));
		allMansionReservations.addAll(boatDiscountReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED));
		return allMansionReservations;
	}

	public boolean overlapsWithActiveReservations(Date startDate, Date endDate, Boat boat) {
		List<AbstractReservation> allReservations = getAllNotCancelledReservationsByBoat(boat);
		for (AbstractReservation ar : allReservations){
			if (ar.getStartDate().before(endDate) && startDate.before(ar.getEndDate())){
				return true;
			}
		}
		return false;
	}

    public Collection<? extends AbstractReservation> getAllNotCancelledReservationsByBoatAndClient(Client client, Long id) {
		List<AbstractReservation> allReservations = new ArrayList<>(boatReservationRepo.findAllByUserAndStatusNotAndBoat(client, ReservationStatus.CANCELLED, boatRepo.findById(id).get()));
		allReservations.addAll(boatDiscountReservationRepo.findAllByUserAndStatusNotAndBoat(client, ReservationStatus.CANCELLED, boatRepo.findById(id).get()));
		return allReservations;
    }
	public List<BoatReservation> getOwnerReservation() {
		BoatOwner fishingInstructor = boatOwnerRepo.findById(authenticationService.getLoggedUser().getId()).get();
		List<Boat> adventures = boatRepo.findAllByBoatOwnerAndDeletedFalse(fishingInstructor);

		List<BoatReservation> adventureReservation= new ArrayList<>();
		for ( Boat adventure: adventures) {
			adventureReservation.addAll(boatReservationRepo.findAllByBoat(adventure));
		}
		return adventureReservation;
	}
	public List<ReservationCalendarDTO> getAllBoatForCalendar()
	{
		List<BoatReservation> adventureReservation=getOwnerReservation();
		List<ReservationCalendarDTO> reservationCalendarDTO=  new ArrayList<>();

		for(BoatReservation a : adventureReservation)
		{
			String title="Avantura:"+a.getBoat().getName();
			String description="Pocetak: "+a.getStartDate()+"\n kraj je "+a.getEndDate()+"\n korisnik koji je rezervisao: "+a.getUser().getName()+ " "+a.getUser().getSurname();
			ReservationCalendarDTO r= new ReservationCalendarDTO(a.getStartDate(),a.getEndDate(),title,description);
			reservationCalendarDTO.add(r);
		}
		return reservationCalendarDTO;

	}
}
