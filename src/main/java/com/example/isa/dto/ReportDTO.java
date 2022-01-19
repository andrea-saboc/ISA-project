package com.example.isa.dto;

import com.example.isa.enums.ReservationType;

public class ReportDTO {
	public String reportText;
	public boolean requestedToSanction;
	public boolean approved;
	public boolean clientShowedUp;
	public Long id;
	public ReservationType reservationType;
	
	public ReportDTO() {}

	@Override
	public String toString() {
		return "ReportDTO [reportText=" + reportText + ", requestedToSanction=" + requestedToSanction + ", approved="
				+ approved + ", clientShowedUp=" + clientShowedUp + ", id=" + id + ", reservationType="
				+ reservationType + "]";
	}

}
