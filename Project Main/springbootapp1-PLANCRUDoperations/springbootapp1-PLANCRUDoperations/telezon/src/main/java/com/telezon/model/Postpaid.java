package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="postpaid")
public class Postpaid {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;

    @Column(name="plan_name")
    private String planName;

    @Column(name="plan_price")
    private Double planPrice;

    @Column(name="plan_bill_cycle")
    private Integer planBillCycle;

    @Column(name="plan_data_cap")
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
