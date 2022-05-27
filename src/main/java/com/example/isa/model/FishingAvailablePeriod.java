package com.example.isa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FishingAvailablePeriod")
public class FishingAvailablePeriod {
    @Id
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "bperiod_id_seq", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "available_period_id")
    public Long id;

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;


    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_id", referencedColumnName = "id", nullable = true)
    public FishingInstructor fishingInstructor;

    public FishingAvailablePeriod() {
    }

    public FishingAvailablePeriod(Long id, Date startDate, Date endDate, FishingInstructor fishingInstructor) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fishingInstructor = fishingInstructor;
    }

    public FishingAvailablePeriod(Date startDate, Date endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public FishingAvailablePeriod(Date startDate, Date endDate,FishingInstructor fishingInstructor) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.fishingInstructor = fishingInstructor;
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

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AvailablePeriod{" +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fishingInstructor=" + fishingInstructor +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fishingInstructor == null) ? 0 : fishingInstructor.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FishingAvailablePeriod other = (FishingAvailablePeriod) obj;
        if (fishingInstructor == null) {
            if (other.fishingInstructor != null)
                return false;
        } else if (!fishingInstructor.equals(other.fishingInstructor))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

}
