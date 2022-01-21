package com.example.isa.mail.formatter;

import com.example.isa.model.AdvertiserComplaint;


public class OwnerComplaintMailFormatter {
	
	private AdvertiserComplaint complaint;
	
    public OwnerComplaintMailFormatter(AdvertiserComplaint c) {
		this.complaint = c;
	}


    public String getSubject() {
        return "You received a complaint";
    }
    
    public String getText() {
        return "Content :" + this.complaint.getComplaintContent();
    }
    public String getOwnerEmail() {
    	return this.complaint.getAdvertiser().getEmail();
    }
}
