package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(nullable = false,name="name")
    private String name;

    @Column(unique = true, nullable = false,name="email")
    private String email;

    @Column(nullable = false,name="phone_number")
    private String phoneNumber;

    // A customer can either choose a prepaid or postpaid plan, not both
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prepaid_plan_id")
    private Prepaid prepaidPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postpaid_plan_id")
    private Postpaid postpaidPlan;

    public Customer() {
    }

    public Customer(Integer customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Prepaid getPrepaidPlan() {
        return prepaidPlan;
    }

    public void setPrepaidPlan(Prepaid prepaidPlan) {
        this.prepaidPlan = prepaidPlan;
    }

    public Postpaid getPostpaidPlan() {
        return postpaidPlan;
    }

    public void setPostpaidPlan(Postpaid postpaidPlan) {
        this.postpaidPlan = postpaidPlan;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
}
