package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telezon.model.Customer;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    List<Customer> findByPrepaidPlanIsNotNull();
    List<Customer> findByPostpaidPlanIsNotNull();
    Customer findByName(String name);
}
