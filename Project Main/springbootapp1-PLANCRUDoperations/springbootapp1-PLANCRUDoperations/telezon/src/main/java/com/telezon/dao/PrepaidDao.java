package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.telezon.model.Prepaid;


@Repository
public interface PrepaidDao  extends JpaRepository<Prepaid,Integer>{
	boolean existsByPlanName(String planName);

}