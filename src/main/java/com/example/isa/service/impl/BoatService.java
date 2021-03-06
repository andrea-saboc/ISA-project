package com.example.isa.service.impl;
import java.util.*;

import com.example.isa.dto.ChangeBoatDto;
import com.example.isa.model.*;
import com.example.isa.model.reservations.AbstractReservation;
import com.example.isa.repository.*;
import com.example.isa.service.impl.reservations.BoatReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.AddAvailablePeriodDto;
import com.example.isa.dto.BoatRegistrationDto;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.service.impl.reservations.CollectingBoatReservationsServiceImpl;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoatService {
	@Autowired
	private BoatsRepository boatsRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private BoatOwnerRepository boatOwnerRepository;
	@Autowired
	private BoatAvailablePeriodRepository availablePeriodRepository;
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;
	@Autowired
	private CollectingBoatReservationsServiceImpl collectingBoatReservationsService;
	@Autowired
	private EntityImageService entityImageService;
	@Autowired
	private RuleRepository ruleRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private BoatReservationServiceImpl boatReservationService;
	@Autowired
	private FishingEquipmentService fishingEquipmentService;

	public BoatService(BoatsRepository br, ImageRepository ir, BoatOwnerRepository bor, BoatAvailablePeriodRepository apr, AdditionalServiceRepository additionalServiceRepository){
		this.boatsRepository = br;
		this.imageRepository = ir;
		this.boatOwnerRepository = bor;
		this.availablePeriodRepository = apr;
		this.additionalServiceRepository = additionalServiceRepository;
	}

	public List<Boat> getAll() {
		List<Boat> boats = boatsRepository.findAll();
		System.out.println("Service boat getting all");
		System.out.println(boats);
		return boats;
	}

    public Boat registerBoat(BoatRegistrationDto dto) {
		System.out.println("In registering boat service!");
		Boat newBoat = createBoat(dto);
		newBoat = boatsRepository.save(newBoat);
		Set<AdditionalService> additionalServices = addBoatToServices(dto.additionalServices, newBoat);
		return newBoat;
    }

	private Set<AdditionalService> addBoatToServices(Set<AdditionalService> additionalServices, Boat boat) {
		Set<AdditionalService> additionalServiceWithBoat = new HashSet<>();
		for (AdditionalService as: additionalServices) {
			AdditionalService as1 = as;
			as1.setBoat(boat);
			additionalServiceRepository.save(as1);
			additionalServiceWithBoat.add(as1);
		}
		return additionalServiceWithBoat;
	}

	public Boat changeBoat(ChangeBoatDto dto) {
		Boat boatToChange = boatsRepository.findById(dto.id).get();
		if(boatToChange == null) return null;
		boatToChange.setName(dto.name);
		boatToChange.setPricePerDay(dto.pricePerDay);
		boatToChange.setPriceForSevenDays(dto.priceForSevenDays);
		boatToChange.setPricePerHour(dto.pricePerHour);
		boatToChange.setLength(dto.length);
		boatToChange.setEnginePower(dto.enginePower);
		boatToChange.setRadar(dto.radar);
		boatToChange.setCancellationPolicy(dto.cancellationPolicy);
		boatToChange.setPromoDescription(dto.promoDescription);
		boatToChange.setType(dto.type);
		boatToChange.setVHFradio(dto.VHFradio);
		boatToChange.setGPS(dto.GPS);
		boatToChange.setMaxSpeed(dto.maxSpeed);
		boatToChange.setNumberOfEngines(dto.numberOfEngines);
		boatToChange.setFishfinder(dto.fishfinder);
		boatToChange.setCapacity(dto.capacity);
		boatToChange.setRules(makeRules(dto));
		boatToChange.setExteriorImages(new HashSet<>(entityImageService.removeAndAddNewExterior(dto, boatToChange)));
		boatToChange.setInteriorImages(new HashSet<>(entityImageService.removeAndAddNewInterior(dto, boatToChange)));
		boatToChange.setFishingEquipments(new HashSet<>(fishingEquipmentService.removeAndAddNewFishingEquipments(dto, boatToChange)));

		Address boatAddress = addressRepository.findById(boatToChange.getAddress().getId()).get();
		boatAddress.setCountry(dto.country);
		boatAddress.setCity(dto.city);
		boatAddress.setAddress(dto.address);
		boatAddress.setLatitude(dto.latitude);
		boatAddress.setLongitude(dto.longitude);
		System.out.println("Boat to change:"+boatToChange);

		addressRepository.save(boatAddress);
		boatToChange = boatsRepository.save(boatToChange);
		return boatToChange;

	}

	@Transactional
	public Boat findLockedById(Long id){
		return boatsRepository.findLockedById(id);
	}

	public boolean isReserved(Long id)
	{
		return boatReservationService.isThereAReservationAfterToday(boatsRepository.findById(id).get());
	}

	private Set<Rule> makeRules(ChangeBoatDto dto) {
		System.out.println("Making rules***");
		Boat boat = boatsRepository.findById(dto.id).get();
		Set<Rule> newRules = new HashSet<>();
		for (Rule r : dto.rules){
			if(r.getRuleId()==-1){
				Rule newRule = new Rule(r.getRule());
				newRule.addBoat(boat);
				newRules.add(newRule);
			}

		}
		for(Integer rid : dto.rulesToDelete){
			Rule rd= ruleRepository.findById(rid).get();
			rd.removeBoat(boat);
			ruleRepository.save(rd);
		}
		return newRules;
	}

	private Boat createBoat(BoatRegistrationDto dto) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
		Address boatAddress = new Address();
		boatAddress.setAddress(dto.address);
		boatAddress.setCity(dto.city);
		boatAddress.setCountry(dto.country);
		boatAddress.setLatitude(dto.latitude);
		boatAddress.setLongitude(dto.longitude);
		Boat newBoat = new Boat();
		newBoat.setBoatOwner(boatOwner);
		//boatOwner.addBoat(newBoat);
		boatOwnerRepository.save(boatOwner);
		newBoat.setName(dto.name);
		newBoat.setPromoDescription(dto.promoDescription);
		newBoat.setType(dto.type);
		newBoat.setLength(dto.length);
		newBoat.setAddress(boatAddress);
		newBoat.setCapacity(dto.capacity);
		newBoat.setFishfinder(dto.fishfinder);
		newBoat.setEnginePower(dto.enginePower);
		newBoat.setNumberOfEngines(dto.numberOfEngines);
		newBoat.setMaxSpeed(dto.maxSpeed);
		newBoat.setRadar(dto.radar);
		newBoat.setGPS(dto.GPS);
		newBoat.setCancellationPolicy(dto.cancellationPolicy);
		newBoat.setVHFradio(dto.VHFradio);
		System.out.println("Exterior images saving"+dto.getExteriorImages().size());
		newBoat.setExteriorImages(new HashSet<>(entityImageService.createAndSaveImages("BoatOwners",boatOwner.getEmail(),dto.getName(),dto.getExteriorImages())));
		System.out.println("Int images saving"+dto.getInteriorImages().size());
		newBoat.setInteriorImages(new HashSet<>(entityImageService.createAndSaveImages("BoatOwners",boatOwner.getEmail(),dto.getName(),dto.getInteriorImages())));
		System.out.println("Donee with saving");
		newBoat.setFishingEquipments(new HashSet<>(dto.getFishingEquipments()));
		newBoat.setRules(convertString2Rule(dto.rules, newBoat));
		newBoat.setPricePerHour(dto.pricePerHour);
		newBoat.setPricePerDay(dto.pricePerDay);
		newBoat.setPriceForSevenDays(dto.priceForSevenDays);
		return newBoat;
	}

	private Set<Image> convertString2Image(Set<String> Images, Boat boat, boolean inter){
		Set<Image> convertedImages = new HashSet<>();
		for (String s: Images) {
			Image newImage = new Image();
			newImage = imageRepository.save(newImage);
			newImage.setImg(s);
			newImage.saveImage();
			convertedImages.add(newImage);
		}
		return convertedImages;
	}

	private Set<Rule> convertString2Rule(Set<String> Rules, Boat boat){
		Set<Rule> convertedRules = new HashSet<>();
		for (String s: Rules){
			Rule newRule = new Rule(s);
			newRule.addBoat(boat);
			convertedRules.add(newRule);
		}
		return convertedRules;
	}

	public Boat getById(Long id) {
		return boatsRepository.findById(id).get();
	}

	public List<Boat> getByOwnerId() {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println("Trying to cast to User:"+ user);
			BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
			List<Boat> ownersBoats = boatsRepository.findAllByBoatOwnerAndDeleted(boatOwner, false);
			return ownersBoats;
		}
		catch (Exception e){
			System.out.println("Trying to cast failed!");
			return null;
		}
	}

	public List<Boat> getByOwner(BoatOwner bo){
		List<Boat> ownersBoats = boatsRepository.findAllByBoatOwnerAndDeleted(bo, false);
		return ownersBoats;
	}

	public List<BoatAvailablePeriod> getBoatAvailbilities(Long boatId) {
		Boat boat = boatsRepository.findById(boatId).get();
		List<BoatAvailablePeriod> boatAvailability = availablePeriodRepository.findByBoat(boat);
		return boatAvailability;
	}

	public List<BoatAvailablePeriod> addBoatAvailabilities(AddAvailablePeriodDto dto) {
		Boat boat = boatsRepository.findById(dto.boatId).get();
		BoatAvailablePeriod availablePeriod = new BoatAvailablePeriod(dto.startTime, dto.endTime, boat);
		if (isThereAReservation(availablePeriod, dto.boatId)){
			return availablePeriodRepository.findByBoat(boat);
		}
		if(insideOtherPeriod(availablePeriod, availablePeriodRepository.findByBoat(boat))) {
			System.out.println("There is a reservation, addBoatAvailability!");
			return availablePeriodRepository.findByBoat(boat);
		}
		availablePeriodRepository.save(availablePeriod);
		List<BoatAvailablePeriod> boatNewAvailability = availablePeriodRepository.findByBoat(boat);
		return boatNewAvailability;
	}

	private boolean insideOtherPeriod(BoatAvailablePeriod availablePeriod, List<BoatAvailablePeriod> byBoat) {
		Boolean overlaps = false;
		System.out.println("Size iss:"+byBoat.size());

		for (BoatAvailablePeriod ma : byBoat){
			if((ma.getStartDate()).before(availablePeriod.getEndDate()) && (availablePeriod.getStartDate().before(ma.getEndDate()))){
				if (ma.getStartDate().after(availablePeriod.getStartDate())){;
					ma.setStartDate(availablePeriod.getStartDate());
				}
				if(ma.getEndDate().before(availablePeriod.getEndDate())){
					ma.setEndDate(availablePeriod.getEndDate());
				}
				availablePeriodRepository.save(ma);
				return true;
			}
		}
		return overlaps;
	}

	private boolean isThereAReservation(BoatAvailablePeriod availablePeriod, Long boatId) {
		Boat boat = boatsRepository.findById(boatId).get();
		List<AbstractReservation> reservations = collectingBoatReservationsService.getAllNotCancelledReservationsByBoat(boat);
		for (AbstractReservation ar : reservations){
			if(ar.getStartDate().after(availablePeriod.getEndDate()) && (availablePeriod.getEndDate().before(ar.getEndDate()))){
				return true;
			}
		}
		return false;
	}

	public List<AddAvailablePeriodDto> getFreeDaysForBoat(Long boatID) {
		List<BoatAvailablePeriod> boatAvailablePeriods = getBoatAvailbilities(boatID);
		List<BoatReservation> boatReservations = collectingBoatReservationsService.getBoatReservationsByBoat(boatID);
		List<AddAvailablePeriodDto> availablePeriods = calculateAvailablePeriods(boatAvailablePeriods, boatReservations);
		return availablePeriods;
    }

	private List<AddAvailablePeriodDto> calculateAvailablePeriods(List<BoatAvailablePeriod> boatAvailablePeriods, List<BoatReservation> boatReservations) {
		List<AddAvailablePeriodDto> availablePeriods = new ArrayList<>();
		for (BoatAvailablePeriod bap: boatAvailablePeriods) {
			availablePeriods.addAll(calculateAvailablePeriodsFromAp(bap, boatReservations));
		}
		return availablePeriods;
	}

	private List<AddAvailablePeriodDto> calculateAvailablePeriodsFromAp(BoatAvailablePeriod bap, List<BoatReservation> boatReservations) {
		List<AddAvailablePeriodDto> availablePeriods = new ArrayList<>();
		List<BoatReservation> overlapedReservations = new ArrayList<>();
		for (BoatReservation br: boatReservations) {
			if ((br.getStartDate().after(bap.getStartDate()) || br.getStartDate().equals(bap.getStartDate()))
					&& (br.getStartDate().before(bap.getEndDate()) || (br.getStartDate().equals(bap.getEndDate())))){
				overlapedReservations.add(br);
				}
			}
		availablePeriods.addAll(separatedAvailablePeriods(sortByAsc(overlapedReservations), bap));
		return availablePeriods;
		}

	private List<AddAvailablePeriodDto> separatedAvailablePeriods(List<BoatReservation> sortByAsc, BoatAvailablePeriod bap) {
		List<AddAvailablePeriodDto> availablePeriods = new ArrayList<>();
		AddAvailablePeriodDto right = new AddAvailablePeriodDto(bap.getStartDate(), bap.getEndDate(), bap.getBoat().getId());
		for (BoatReservation br : sortByAsc){
			if (!br.getStartDate().equals(right.startTime)){
				availablePeriods.add(new AddAvailablePeriodDto(right.startTime, br.getStartDate(), right.boatId));
			}
			right.startTime = br.getEndDate();
		}
		if (!right.startTime.equals(right.endTime)){
			availablePeriods.add(right);
		}
		return availablePeriods;
	}

	private List<BoatReservation> sortByAsc(List<BoatReservation> overlapedReservations) {
		for(int i=0; i<overlapedReservations.size(); i++){
			for(int j = i+1; j<overlapedReservations.size(); j++){
				if (overlapedReservations.get(i).getStartDate().before(overlapedReservations.get(j).getStartDate())){
					BoatReservation temp = overlapedReservations.get(i);
					overlapedReservations.set(i, overlapedReservations.get(j));
					overlapedReservations.set(j, temp);
				}

			}
		}
		return overlapedReservations;
	}

	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public void deleteBoat(Long boatId) {
		Boat entity = boatsRepository.findLockedById(boatId);
		if (entity == null)
			throw new PessimisticLockingFailureException("Some is already trying to reserve!");
		Boat boat = boatsRepository.findById(boatId).get();
		boat.setDeleted(true);
		boatsRepository.save(boat);
	}


	public boolean overlapsWithAvailability(Date startDate, Date endDate, Long id) {
		List<BoatAvailablePeriod> mansionAvailablePeriods = getBoatAvailbilities(id);
		for (BoatAvailablePeriod ma : mansionAvailablePeriods ){
			if(ma.getStartDate().before(endDate) && startDate.before(ma.getStartDate())){
				return true;
			}
		}
		return false;
	}

	public boolean inAvailabilityPeriods(Date startDate, Date endDate, Long id) {
		List<BoatAvailablePeriod> mansionAvailablePeriods = getBoatAvailbilities(id);
		for (BoatAvailablePeriod ma : mansionAvailablePeriods ){
			if(!startDate.after(ma.getEndDate()) && !ma.getStartDate().after(endDate)){
				return true;
			}
		}
		return false;
	}
}
