package com.example.isa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import com.example.isa.model.*;
import com.example.isa.repository.*;
import com.example.isa.service.AdventureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class IsaApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	AdventureService as;

	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	
	@Autowired
	BoatReservationRepository repo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	BoatAvailablePeriodRepository periodRepo;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	AdditionalServiceRepository additionalServiceRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	MansionAvailablePeriodRepository mperiodRepo;
	@Autowired
	MansionRepository mrepo;
    
	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		
		Mansion m = mrepo.findById(1);
		MansionAvailablePeriod a11 = new MansionAvailablePeriod(new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(),new GregorianCalendar(2022, Calendar.JANUARY, 10).getTime(),m);
		MansionAvailablePeriod a22 = new MansionAvailablePeriod(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime(),new GregorianCalendar(2022, Calendar.JANUARY, 28).getTime(),m);
		MansionAvailablePeriod a33 = new MansionAvailablePeriod(new GregorianCalendar(2022, Calendar.MARCH, 1).getTime(),new GregorianCalendar(2022, Calendar.MARCH, 16).getTime(),m);
		MansionAvailablePeriod a44 = new MansionAvailablePeriod(new GregorianCalendar(2022, Calendar.JUNE, 1).getTime(),new GregorianCalendar(2022, Calendar.JUNE, 21).getTime(),m);
		
		mperiodRepo.save(a11);
		mperiodRepo.save(a22);		
		mperiodRepo.save(a33);		
		mperiodRepo.save(a44);

		
		

		/*
		Boat b = boatRepo.findByName("Milicija");
		

		Client c = new Client("Igor","Stojanovic", "Address", "City", "Serbiania", "468438",
				"littlevamp999@gmail.com", "igi", 0,0);
		c.setPassword(passwordEncoder.encode("igi"));
		userRepo.save(c);
		
		//repo.save(new BoatReservation(c, new Date(), 66, 77, b1));
		//repo.save(new BoatReservation(c, new Date(), 4444, 77, b2));

		
    	Iterable<BoatReservation> res = repo.findAllByUser(c);
    	
    	for(BoatReservation r: res){
    		System.out.println(r.getBoat().getName());
    	}
    	*/

	}
	


}
