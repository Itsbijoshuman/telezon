package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="postpaid")
public class Postpaid {

    @Id
    @NotNull(message = "Plan ID is required")
    private Integer planId;

    @Column(name="plan_name")
    @NotNull(message = "Plan Name is required")
    @Size(min = 2, max = 100, message = "Plan Name must be between 2 and 100 characters")
    private String planName;

    @Column(name="plan_price")
    @NotNull(message = "Plan Price is required")
    @Min(value = 0, message = "Plan Price must be greater than or equal to 0")
    private Double planPrice;

    @Column(name="plan_bill_cycle")
    @NotNull(message = "Plan Bill Cycle is required")
    @Min(value = 1, message = "Plan Bill Cycle must be greater than or equal to 1")
    private Integer planBillCycle;

    @Column(name="plan_data_cap")
    @NotNull(message = "Plan Data Cap is required")
    @Min(value = 0, message = "Plan Data Cap must be greater than or equal to 0")
    private Double planDataCap;

    public Postpaid() {}

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

    public Integer getPlanBillCycle() {
        return planBillCycle;
    }

    public void setPlanBillCycle(Integer planBillCycle) {
        this.planBillCycle = planBillCycle;
    }

    public Double getPlanDataCap() {
        return planDataCap;
    }

    public void setPlanDataCap(Double planDataCap) {
        this.planDataCap = planDataCap;
    }

    @Override
    public String toString() {
        return "Postpaid [planId=" + planId + ", planName=" + planName + ", planPrice=" + planPrice + 
                ", planBillCycle=" + planBillCycle + ", planDataCap=" + planDataCap + "]";
    }
}
