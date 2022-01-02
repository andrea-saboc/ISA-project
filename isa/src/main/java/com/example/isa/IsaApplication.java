package com.example.isa;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.isa.model.AvailablePeriod;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.Client;
import com.example.isa.repository.AvailablePeriodRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.UserRepository;
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
	private PasswordEncoder passwordEncoder;
    
	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		
		Boat b1 = new Boat("Milicija", "promo milicije");
		Boat b2 = new Boat("Malicija", "promo malicije");		
		boatRepo.save(b1);
		boatRepo.save(b2);		
		
		AvailablePeriod a1 = new AvailablePeriod(new Date(),new Date(),b1);
		AvailablePeriod a2 = new AvailablePeriod(new Date(),new Date(),b1);
		AvailablePeriod a3 = new AvailablePeriod(new Date(),new Date(),b2);
		AvailablePeriod a4 = new AvailablePeriod(new Date(),new Date(),b2);
		
		
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
