package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.PrepaidDao;
import com.telezon.exception.PrepaidPlanNotFoundException;
import com.telezon.exception.DuplicatePrepaidPlanException;
import com.telezon.model.Prepaid;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrepaidService {

    @Autowired
    private PrepaidDao prepaidDao;

    public List<Prepaid> getPrepaidPlans() {
        return prepaidDao.findAll();
    }

    public Prepaid getPrepaidPlanById(Integer planId) {
        return prepaidDao.findById(planId)
        		.orElseThrow(() -> new PrepaidPlanNotFoundException("Prepaid Plan not found" +planId));
    }
    
    public void addPrepaidPlan(Prepaid prepaid) {
        if (prepaidDao.existsByPlanName(prepaid.getPlanName())) {
        	throw new DuplicatePrepaidPlanException("A plan with this name already exists");
        }
        if (prepaid.getPlanId() == null || prepaid.getPlanId() <= 0) {
            throw new IllegalArgumentException("Invalid Plan ID provided. Plan ID must be non-null and positive.");
        }
    	prepaidDao.save(prepaid);
    }
}
