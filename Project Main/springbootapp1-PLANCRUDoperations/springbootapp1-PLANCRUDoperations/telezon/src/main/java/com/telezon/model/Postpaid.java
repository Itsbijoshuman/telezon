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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer plan_id;

    @Column(name="plan_name")
    String plan_name;

    @Column(name="plan_price")
    Double plan_price;

    @Column(name="plan_bill_cycle")
    Integer plan_bill_cycle;

    @Column(name="plan_data_cap")
    Double plan_data_cap;

    @Override
    public String toString() {
        return "postpaid [plan_id=" + plan_id +
               ", plan_name=" + plan_name +
               ", plan_price=" + plan_price +
               ", plan_bill_cycle=" + plan_bill_cycle +
               ", plan_data=" + plan_data_cap + "]";
    }

    public Integer getPlanId() {
        return plan_id;
    }

    public void setPlanId(Integer plan_id) {
        this.plan_id = plan_id;
    }

    public Double getPlanPrice() {
        return plan_price;
    }

    public void setPlanPrice(Double plan_price) {
        this.plan_price = plan_price;
    }

    public String getPlanName() {
        return plan_name;
    }

    public void setPlanName(String plan_name) {
        this.plan_name = plan_name;
    }

    public Integer getPlanBillCycle() {
        return plan_bill_cycle;
    }

    public void setPlanBillCycle(Integer plan_duration) {
        this.plan_bill_cycle = plan_duration;
    }

    public Double getPlanDataCap() {
        return plan_data_cap;
    }

    public void setPlanDataCap(Double plan_data_limit) {
        this.plan_data_cap = plan_data_limit;
    }
}
