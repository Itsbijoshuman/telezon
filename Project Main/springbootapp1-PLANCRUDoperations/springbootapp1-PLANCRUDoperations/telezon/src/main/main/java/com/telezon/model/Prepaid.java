package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="prepaid")
public class Prepaid {

    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer planId;

    @Column(name="plan_name")
    @NotBlank(message = "Plan name cannot be empty")
    @Size(min = 1, max = 50, message = "Plan name must be between 1 and 50 characters")
    private String planName;

    @Column(name="plan_price")
    @NotNull(message = "Plan price cannot be null")
    @Min(value = 0, message = "Plan price must be a positive number")  
    private Double planPrice;

    @Column(name="plan_limit")
    @NotNull(message = "Plan limit cannot be null")
    @Min(value = 0, message = "Plan limit must be a positive number")
    private Double planLimit;

    @Column(name="duration")
    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Duration must be at least 1 day")
    private Integer duration;


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
