package com.telezon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
public class Data {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double totalData;
    private Double usedData;

    // Constructors
    public Data() {}

    public Data(Integer id, Customer customer, Double totalData, Double usedData) {
        this.id = id;
        this.customer = customer;
        this.totalData = totalData;
        this.usedData = usedData;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalData() {
        return totalData;
    }

    public void setTotalData(Double totalData) {
        this.totalData = totalData;
    }

    public Double getUsedData() {
        return usedData;
    }

    public void setUsedData(Double usedData) {
        this.usedData = usedData;
    }

    public Double getRemainingData() {
        return totalData - usedData; // Calculated in Java
    }
}
