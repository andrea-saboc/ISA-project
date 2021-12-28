package com.example.isa;

import com.example.isa.dto.AdvertisersDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.Client;
import com.example.isa.model.Reservation;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.UserRepository;
import com.example.isa.service.implemented.AdventureService;

import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;




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
    
	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		
		Boat b1 = new Boat("Milicija", "promo pretp", "smth", 0);
		Boat b2 = new Boat("Malicija", "promo pretp", "smth", 0);		
		boatRepo.save(b1);
		boatRepo.save(b2);
		
		Client c = new Client("Igor","Stojanovic", "Address", "City", "Serbiania", "468438",
				"igi@gmail.com", "igi", 0,0);
		userRepo.save(c);
		
		repo.save(new BoatReservation(null, c, null, 66, 77, b1));
		repo.save(new BoatReservation(null, c, null, 4444, 77, b2));

		
    	Iterable<BoatReservation> res = repo.findAllByUser(c);
    	
    	for(BoatReservation r: res){
    		System.out.println(r.getBoat().getName());
    	}
		

	}
	


}
