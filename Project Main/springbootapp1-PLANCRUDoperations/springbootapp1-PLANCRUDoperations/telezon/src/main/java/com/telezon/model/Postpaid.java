package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="postpaid")
public class Postpaid {

    @Id
    Integer plan_id;

    @Column
    String plan_name;

    @Column
    Double plan_price;

    @Column
    Integer plan_duration;

    @Column
    Double plan_data_limit;

    @Override
    public String toString() {
        return "postpaid [plan_id=" + plan_id +
               ", plan_name=" + plan_name +
               ", plan_price=" + plan_price +
               ", plan_duration=" + plan_duration +
               ", plan_data_limit=" + plan_data_limit + "]";
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

    public Integer getPlanDuration() {
        return plan_duration;
    }

    public void setPlanDuration(Integer plan_duration) {
        this.plan_duration = plan_duration;
    }

    public Double getPlanDataLimit() {
        return plan_data_limit;
    }

    public void setPlanDataLimit(Double plan_data_limit) {
        this.plan_data_limit = plan_data_limit;
    }
}
