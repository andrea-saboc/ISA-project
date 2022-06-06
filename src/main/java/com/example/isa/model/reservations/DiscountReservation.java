package com.example.isa.model.reservations;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.isa.model.ReservationReport;
import com.example.isa.model.User;

/**
 * @author PC
 *
 */
@Entity
@Table(name = "discount_reservation")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DiscountReservation extends AbstractReservation implements Serializable{

	private static final long serialVersionUID = 6715093951843998707L;
	
    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version = 0L;
    
	private String type="";
		
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "system_user")
	private User user;

	private Date startDate;
	private Date endDate;
	private int numberOfGuests;
	private double priceWithDiscount;
	private double priceWithoutDiscount;
	private double percentageOfDiscount;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	private Date validUntil;
	
	@OneToOne
	private ReservationReport report;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="discount_reservation_services",
			joinColumns=@JoinColumn(name="discount_reservation_id"),
			inverseJoinColumns=@JoinColumn(name="service_id "))
	public Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();


	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public ReservationReport getReport() {
		return report;
	}
	public void setReport(ReservationReport report) {
		this.report = report;
	}
	public double getPriceWithoutDiscount() {
		return priceWithoutDiscount;
	}
	public void setPriceWithoutDiscount(double priceWithoutDiscount) {
		this.priceWithoutDiscount = priceWithoutDiscount;
	}
	public double getPercentageOfDiscount() {
		return percentageOfDiscount;
	}
	public void setPercentageOfDiscount(double percentageOfDiscount) {
		this.percentageOfDiscount = percentageOfDiscount;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public double getPriceWithDiscount() {
		return priceWithDiscount;
	}
	public void setPriceWithDiscount(double priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}

	public ReservationStatus getStatus() {
		return status;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public DiscountReservation() {}

	public DiscountReservation(String type, Date startDate, Date endDate, int numberOfGuests,
			double priceWithDiscount) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfGuests = numberOfGuests;
		this.priceWithDiscount = priceWithDiscount;
		this.status = ReservationStatus.ACTIVE;
	}

	public double calculatePercentageOfDiscount() {
		return 100.00 - getPriceWithDiscount()/getPriceWithDiscount();

	}
	@Override
	public String toString() {
		return "DiscountReservation{" +
				"id=" + id +
				", user=" + user.getId() +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}

	public abstract String getEntityName();
}
