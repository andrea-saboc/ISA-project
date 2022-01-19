package com.example.isa.constants;

import com.example.isa.dto.ClientFeedbackDto;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;

public class FeedbackConstants {
	
	public FeedbackConstants() {}
	
	public static final User CLIENT = new User();
	
	public static final Boat BOAT = new Boat() {{this.setAvgGrade(0);}};
	
	public static final Mansion MANSION = new Mansion(){{this.setAvgGrade(0);}};
	

	
	public static final ClientFeedbackDto BOAT_FEEDBACK = new ClientFeedbackDto("Feedback content boat", 4, 1L);
	
	public static final ClientFeedbackDto MANSION_FEEDBACK = new ClientFeedbackDto("Feedback content mansion", 4, 1L);
	
	public static final BoatReservation BOAT_RESERVATION = new BoatReservation();
	
	public static final MansionReservation MANSION_RESERVATION = new MansionReservation();
	


}
