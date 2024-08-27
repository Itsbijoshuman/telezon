package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="prepaid_plan")
    private Integer prepaidPlan; 

    @Column(name="postpaid_plan")
    private Integer postpaidPlan; 

    @Column(name="remaining_balance")
    private Double remainingBalance;  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPrepaidPlan() {
        return prepaidPlan;
    }

    public void setPrepaidPlan(Integer prepaidPlan) {
        this.prepaidPlan = prepaidPlan;
    }

    public Integer getPostpaidPlan() {
        return postpaidPlan;
    }

    public void setPostpaidPlan(Integer postpaidPlan) {
        this.postpaidPlan = postpaidPlan;
    }

    public Double getRemainingBalance() {   
        return remainingBalance;
    }

    public void setRemainingBalance(Double remainingBalance) {  // Setter for the new column
        this.remainingBalance = remainingBalance;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id +
               ", name=" + name +
               ", email=" + email +
               ", phoneNumber=" + phoneNumber +
               ", prepaidPlan=" + prepaidPlan +
               ", postpaidPlan=" + postpaidPlan +
               ", remainingBalance=" + remainingBalance + "]";  // Included in toString()
    }
}
