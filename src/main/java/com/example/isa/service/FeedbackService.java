package com.example.isa.service;

import com.example.isa.dto.ClientFeedbackDto;
import com.example.isa.model.ClientFeedback;

public interface FeedbackService {

	public ClientFeedback addEntityFeedback(ClientFeedbackDto dto);
	
	public ClientFeedback addOwnerFeedback(ClientFeedbackDto dto);
	
}
