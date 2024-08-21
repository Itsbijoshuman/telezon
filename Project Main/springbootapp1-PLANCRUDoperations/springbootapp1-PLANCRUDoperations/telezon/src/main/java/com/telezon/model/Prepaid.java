package com.telezon.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="prepaid")
public class Prepaid {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer plan_id;
	
    @Column(name="plan_name")
	String plan_name;
    
    @Column(name="plan_price")
	Double plan_price;
    
    @Column(name="plan_duration")
	Integer plan_duration;
    
    @Column(name="plan_data_limit")
	Double plan_data_limit;
	
	@Override
	public String toString() {
		return "prepaid [plan_id=" + plan_id + ", plan_name=" + plan_name + ", plan_price=" + plan_price+ " plan_duration=" + plan_duration + ", plan_data_limit="+plan_data_limit+"]";
	}
	public Integer getPid() {
		return plan_id;
	}
	public void setPid(Integer plan_id) {
		this.plan_id = plan_id;
	}
	
	public Double getPlanPrice() {
		return plan_price;
	}
	public void setPlanPrice(Double plan_price) {
		this.plan_price = plan_price;
	}
	
	public String getPname() {
		return plan_name;
	}
	public void setPname(String pname) {
		this.plan_name = plan_name;
	}	
	public Integer getDuration() {
		return plan_duration;
	}
	public void setDuration(Integer plan_duration) {
		this.plan_duration = plan_duration;
	}
	public void setPlanLimit(Double plan_duration) {
		this.plan_data_limit = plan_data_limit;
	}
	public Double getPlanLimit() {
		return plan_data_limit;
	}
	
}
