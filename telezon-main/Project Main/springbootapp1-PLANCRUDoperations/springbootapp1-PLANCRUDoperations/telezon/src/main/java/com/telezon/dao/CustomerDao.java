package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telezon.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
}
