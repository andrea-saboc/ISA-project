package com.example.isa.constants;

import com.example.isa.dto.ClientFeedbackDto;
import com.example.isa.model.Boat;
import com.example.isa.model.ClientFeedback;

public class FeedbackConstants {
	
	private FeedbackConstants() {}
	
	public static final Boat BOAT = new Boat("Malicija", "Promo Malicije..",0,0);
	
	public static final ClientFeedback f1 = new ClientFeedback("Zadovoljan sam totalno",3,"igi@gmail.com");
	
	public static ClientFeedbackDto FEEDBACK = new ClientFeedbackDto("Content feedback-aa", 4, 0);

}
