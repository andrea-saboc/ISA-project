package com.example.isa.dto;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.MansionReservation;

public class ActiveReservationDto {
	
	private long reservationId;
	private String reservationType;
	
	private String startDate;
	private String endDate;
	private int numberOfGuests;	
	private List<String> additinalServices;
	private double totalPrice;
	
	private String entityName;
	private String entityAddress;
	private String entityPromo;

	private boolean allowedCancelation;

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public List<String> getAdditinalServices() {
		return additinalServices;
	}

	public void setAdditinalServices(List<String> additinalServices) {
		this.additinalServices = additinalServices;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityAddress() {
		return entityAddress;
	}

	public void setEntityAddress(String entityAddress) {
		this.entityAddress = entityAddress;
	}

	public String getEntityPromo() {
		return entityPromo;
	}

	public void setEntityPromo(String entityPromo) {
		this.entityPromo = entityPromo;
	}

	public boolean isAllowedCancelation() {
		return allowedCancelation;
	}

	public void setAllowedCancelation(boolean allowedCancelation) {
		this.allowedCancelation = allowedCancelation;
	}

	public ActiveReservationDto(long reservationId, String reservationType, String startDate, String endDate,
			int numberOfGuests, List<String> additinalServices, double totalPrice, String entityName,
			String entityAddress, String entityPromo, boolean allowedCancelation) {
		super();
		this.reservationId = reservationId;
		this.reservationType = reservationType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfGuests = numberOfGuests;
		this.additinalServices = additinalServices;
		this.totalPrice = totalPrice;
		this.entityName = entityName;
		this.entityAddress = entityAddress;
		this.entityPromo = entityPromo;
		this.allowedCancelation = allowedCancelation;
	}

	public ActiveReservationDto() {
		super();
	}
	
	public ActiveReservationDto(MansionDiscountReservation r) {

		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		
		this.reservationId = r.getId();
		this.reservationType = r.getType();
		this.startDate = formatter.format(r.getStartDate());
		this.endDate = formatter.format(r.getEndDate());
		this.numberOfGuests = r.getNumberOfGuests();
		this.additinalServices = null;
		this.totalPrice = r.getPriceWithDiscount();
		this.entityName = r.getMansion().getName();
		this.entityPromo = r.getMansion().getPromoDescription();
		this.entityAddress = r.getMansion().getAddress().getCountry() + ", "+r.getMansion().getAddress().getCity()+", "+r.getMansion().getAddress().getAddress();		
	}
	
	public ActiveReservationDto(BoatDiscountReservation r) {

		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		
		this.reservationId = r.getId();
		this.reservationType = r.getType();
		this.startDate = formatter.format(r.getStartDate());
		this.endDate = formatter.format(r.getEndDate());
		this.numberOfGuests = r.getNumberOfGuests();
		this.additinalServices = null;
		this.totalPrice = r.getPriceWithDiscount();
		this.entityName = r.getBoat().getName();
		this.entityPromo = r.getBoat().getPromoDescription();
		this.entityAddress = r.getBoat().getAddress().getCountry() + ", "+r.getBoat().getAddress().getCity()+", "+r.getBoat().getAddress().getAddress();		
	}
	
	public ActiveReservationDto(BoatReservation r) {

		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		
		this.reservationId = r.getId();
		this.reservationType = r.getType();
		this.startDate = formatter.format(r.getStartDate());
		this.endDate = formatter.format(r.getEndDate());
		this.numberOfGuests = r.getNumberOfGuests();
		this.additinalServices = null;
		this.totalPrice = r.getTotalPrice();
		this.entityName = r.getBoat().getName();
		this.entityPromo = r.getBoat().getPromoDescription();
		this.entityAddress = r.getBoat().getAddress().getCountry() + ", "+r.getBoat().getAddress().getCity()+", "+r.getBoat().getAddress().getAddress();		
	}
	
	public ActiveReservationDto(MansionReservation r) {

		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		
		this.reservationId = r.getId();
		this.reservationType = r.getType();
		this.startDate = formatter.format(r.getStartDate());
		this.endDate = formatter.format(r.getEndDate());
		this.numberOfGuests = r.getNumberOfGuests();
		this.additinalServices = null;
		this.totalPrice = r.getTotalPrice();
		this.entityName = r.getMansion().getName();
		this.entityPromo = r.getMansion().getPromoDescription();
		this.entityAddress = r.getMansion().getAddress().getCountry() + ", "+r.getMansion().getAddress().getCity()+", "+r.getMansion().getAddress().getAddress();		
	}
	
	
	
}
