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
    
    private Double instanceCharge;  

    public Data() {}

    public Data(Integer id, Customer customer, Double totalData, Double usedData) {
        this.id = id;
        this.customer = customer;
        this.totalData = totalData;
        this.usedData = usedData;
    }

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
    
    public Double getInstanceCharge() {   // Getter for instanceCharge
        return instanceCharge;
    }

    public void setInstanceCharge(Double instanceCharge) {  // Setter for instanceCharge
        this.instanceCharge = instanceCharge;
    }


    public Double getRemainingData() {
   	 if (totalData == null ) {
		 return 0.0; // Or return some default value like 0.0
	    }
        return totalData - usedData; // Calculated in Java
    }
}
