package com.example.isa.model;

import java.util.Date;

public class AvailablePeriod {
	
	private Date startDate;
	private Date endDate;
	

	public AvailablePeriod(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public AvailablePeriod() {}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
