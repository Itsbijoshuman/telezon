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

    private String fromName;  
    private Double totalDuration;
    private Double usedDuration;
    private String toName;  
    private Double instanceCharge;  // New field for instance charge

    // Default constructor
    public Call() {}

    // Parameterized constructor
    public Call(Integer cid, String fromName, Double totalDuration, Double usedDuration, String toName) {
        this.cid = cid;
        this.fromName = fromName;
        this.totalDuration = totalDuration;
        this.usedDuration = usedDuration;
        this.toName = toName;
    }

    // Getters and Setters for all fields
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

    public Double getInstanceCharge() {   // Getter for instanceCharge
        return instanceCharge;
    }

    public void setInstanceCharge(Double instanceCharge) {  // Setter for instanceCharge
        this.instanceCharge = instanceCharge;
    }

    // Calculated field for remaining duration
    public Double getRemainingDuration() {
    	 if (totalDuration == null ) {
    		 return 0.0; // Or return some default value like 0.0
    	    }
    	    return totalDuration - usedDuration;
    }

    @Override
    public String toString() {
        return "Call [cid=" + cid +
               ", fromName=" + fromName +
               ", totalDuration=" + totalDuration +
               ", usedDuration=" + usedDuration +
               ", toName=" + toName +
               ", instanceCharge=" + instanceCharge + "]";  // Included in toString()
    }
}
