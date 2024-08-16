package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telezon.model.Plan;

@Repository
//@Component
public interface PlanDao  extends JpaRepository<Plan,Integer>{

}
