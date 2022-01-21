package com.example.isa.mail.formatter;

import com.example.isa.model.BoatComplaint;

public class BoatComplaintFormatter {
	
	private BoatComplaint complaint;
	
	public BoatComplaintFormatter(BoatComplaint c) {
		this.complaint = c;		
	}
	
    public String getText() {
    	
    	String content = "";
    	content += " Complaint content : " + complaint.getComplaintContent();
        return content ;
    }

    public String getSubject() {
        return "You received a complaint for your boat " + complaint.getBoat().getName();
    }
    
    public String getOwnerEmail() {
    	return complaint.getBoat().getBoatOwner().getEmail();
    }
}
