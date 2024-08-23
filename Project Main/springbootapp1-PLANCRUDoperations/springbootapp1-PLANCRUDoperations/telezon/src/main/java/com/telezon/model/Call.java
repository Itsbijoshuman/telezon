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
    private String fromName;  // Updated field name
    private Double totalDuration;
    private Double usedDuration;
    private String toName;  // New field

    // Constructors
    public Call() {}

    public Call(Integer cid, String fromName, Double totalDuration, Double usedDuration, String toName) {
        this.cid = cid;
        this.fromName = fromName;
        this.totalDuration = totalDuration;
        this.usedDuration = usedDuration;
        this.toName = toName;
    }

    // Getters and Setters
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
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

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Double getRemainingDuration() {
        return totalDuration - usedDuration; // Calculated in Java
    }
}
