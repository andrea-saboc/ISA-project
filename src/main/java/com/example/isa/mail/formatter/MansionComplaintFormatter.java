package com.example.isa.mail.formatter;

import com.example.isa.model.MansionComplaint;

public class MansionComplaintFormatter{
	
	private MansionComplaint complaint;
	
	public MansionComplaintFormatter(MansionComplaint c) {
		this.complaint = c;
		
	}

    public String getText() {
    	
    	String content = "";
    	content += " Complaint content : " + complaint.getComplaintContent();
        return content ;
    }

    public String getSubject() {
        return "You received a complaint for your mansion "+ complaint.getMansion().getName();
    }
    
    public String getOwnerEmail() {
    	return complaint.getMansion().getMansionOwner().getEmail();
    }
}
