package com.telezon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
public class Data {

    @Id
    private Integer id;
    private String name;
    private Double totalData;
    private Double usedData;

    // Constructors
    public Data() {}

    public Data(Integer id, String name, Double totalData, Double usedData) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
