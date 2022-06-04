package com.example.isa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.isa.model.*;
import com.example.isa.model.reservations.AbstractReservation;
import com.example.isa.service.impl.reservations.CollectingBoatReservationsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.AdvertisersDto;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.FishingInstructorRepository;
import com.example.isa.repository.MansionOwnerRepository;
import com.example.isa.repository.UserRepository;


@Service
public class AdvertisersService {
	@Autowired
	BoatService boatService;
	@Autowired
	CollectingBoatReservationsServiceImpl collectingBoatReservationsService;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private BoatOwnerRepository boatOwnerRepository;
	public List<BoatOwner> boats;
	@Autowired
    private MansionOwnerRepository mansionnOwnerRepository;
	public List<MansionOwner> mansionOwners;
	@Autowired
    private FishingInstructorRepository fishingInstructorRepository;
	public List<FishingInstructor> fishingInstructors;
	
	public AdvertisersService(MansionOwnerRepository mansionOwnerRepository, BoatOwnerRepository boatOwnerRepository,FishingInstructorRepository fishnigInstructorRepository) {
        this.mansionnOwnerRepository = mansionOwnerRepository;
        this.boatOwnerRepository = boatOwnerRepository;
        this.fishingInstructorRepository=fishnigInstructorRepository;
    }
	
    public List<BoatOwner> getAllBoatOwner()
    {
       return boatOwnerRepository.findAll();
    }
    public List<MansionOwner> getAllMansionOwner()
    {
       return mansionnOwnerRepository.findAll();
    }
    public List<FishingInstructor> getAllFishingInstructor()
    {
       return fishingInstructorRepository.findAll();
    }
    
    public List<AdvertisersDto> getAllAdvertisers()
    {
    	List<AdvertisersDto> advertisers=new ArrayList<AdvertisersDto>();
    	boats=getAllBoatOwner();
    	mansionOwners=getAllMansionOwner();
    	fishingInstructors=getAllFishingInstructor();
    	for(final BoatOwner boat : boats)
    	{
    	  AdvertisersDto advertiser = new AdvertisersDto(boat.getEmail(),boat.getReason());
    	  advertisers.add(advertiser);
    	}
    	for(final MansionOwner mansionOwner : mansionOwners)
    	{
    	  AdvertisersDto advertiser = new AdvertisersDto(mansionOwner.getEmail(),mansionOwner.getReason());
    	  advertisers.add(advertiser);
    	}
    	for(final FishingInstructor fishing : fishingInstructors)
    	{
    	  AdvertisersDto advertiser = new AdvertisersDto(fishing.getEmail(),fishing.getReason());
    	  advertisers.add(advertiser);
    	}

    	return advertisers;
    	
    }


    public boolean boatOwnerAvailable(User owner, Date startDate, Date endDate) {
		List<AbstractReservation> allBoatOwnerReservations = new ArrayList<>();
		allBoatOwnerReservations.addAll(collectingBoatReservationsService.getOwnersNotCanceledReservations(owner));
		for (AbstractReservation ab : allBoatOwnerReservations){
			if(!(startDate.after(ab.getEndDate())) && !(ab.getEndDate().after(endDate))){
				return false;
			}
		}
		return true;

    }
}
