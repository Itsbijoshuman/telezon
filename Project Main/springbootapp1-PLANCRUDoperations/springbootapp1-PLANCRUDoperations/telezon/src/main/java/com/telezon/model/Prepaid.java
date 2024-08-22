package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="prepaid")
public class Prepaid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;

    @Column(name="plan_name")
    private String planName;

    @Column(name="plan_price")
    private Double planPrice;

    @Column(name="plan_limit")
    private Double planLimit;

    @Column(name="duration")
    private Integer duration;

    // Getters and Setters

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public Double getPlanLimit() {
        return planLimit;
    }

    public void setPlanLimit(Double planLimit) {
        this.planLimit = planLimit;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Prepaid [planId=" + planId +
               ", planName=" + planName +
               ", planPrice=" + planPrice +
               ", planLimit=" + planLimit +
               ", duration=" + duration + "]";
    }
}
