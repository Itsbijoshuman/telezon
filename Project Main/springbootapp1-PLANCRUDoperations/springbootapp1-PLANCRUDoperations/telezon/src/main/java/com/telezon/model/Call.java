package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "calls")
public class Call {

    @Id
    private Integer cid;
    private String name;
    private Double totalDuration;
    private Double usedDuration;

    // Constructors
    public Call() {}

    public Call(Integer cid, String name, Double totalDuration, Double usedDuration) {
        this.cid = cid;
        this.name = name;
        this.totalDuration = totalDuration;
        this.usedDuration = usedDuration;
    }

    // Getters and Setters
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Double getUsedDuration() {
        return usedDuration;
    }

    public void setUsedDuration(Double usedDuration) {
        this.usedDuration = usedDuration;
    }

    public Double getRemainingDuration() {
        return totalDuration - usedDuration; // Calculated in Java
    }
}
