package com.example.isa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import com.example.isa.model.*;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.isa.service.implemented.AdventureService;




@SpringBootApplication
@EnableWebMvc
public class IsaApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	AdventureService as;
	
	@Autowired
	BoatReservationRepository repo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	AvailablePeriodRepository periodRepo;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		
		Boat b1 = new Boat("Milicija", "promo milicije");
		Boat b2 = new Boat("Malicija", "promo malicije");
		Address adr1 = new Address("Marka Mljanova 30", "Novi Sad", "Srbija");
		b1.setAddress(adr1);
		Address adr2 = new Address("St Marcus Aurelius 23/788", "Honolulu", "Hawaii" );
		b2.setAddress(adr2);
		//addressRepository.save(adr1);
		//addressRepository.save(adr2);
		boatRepo.save(b1);
		boatRepo.save(b2);		
		
		AvailablePeriod a1 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(),new GregorianCalendar(2022, Calendar.JANUARY, 10).getTime(),b1);
		AvailablePeriod a2 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime(),new GregorianCalendar(2022, Calendar.JANUARY, 28).getTime(),b1);
		AvailablePeriod a3 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.MARCH, 1).getTime(),new GregorianCalendar(2022, Calendar.MARCH, 16).getTime(),b2);
		AvailablePeriod a4 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JUNE, 1).getTime(),new GregorianCalendar(2022, Calendar.JUNE, 21).getTime(),b2);
		
		
		periodRepo.save(a1);
		periodRepo.save(a2);		
		periodRepo.save(a3);		
		periodRepo.save(a4);
		
		Set<AvailablePeriod> availablePeriods = new HashSet<AvailablePeriod>();
		availablePeriods.add(new AvailablePeriod(new Date(),new Date()));
		availablePeriods.add(new AvailablePeriod(new Date(),new Date()));
		availablePeriods.add(new AvailablePeriod(new Date(),new Date()));
		
		

		
		Boat b = boatRepo.findByName("Milicija");
		

		Client c = new Client("Igor","Stojanovic", "Address", "City", "Serbiania", "468438",
				"igi@gmail.com", "igi", 0,0);
		c.setPassword(passwordEncoder.encode("igi"));
		userRepo.save(c);
		
		repo.save(new BoatReservation(c, new Date(), 66, 77, b1));
		repo.save(new BoatReservation(c, new Date(), 4444, 77, b2));

		
    	Iterable<BoatReservation> res = repo.findAllByUser(c);
    	
    	for(BoatReservation r: res){
    		System.out.println(r.getBoat().getName());
    	}	


	}
	


}
