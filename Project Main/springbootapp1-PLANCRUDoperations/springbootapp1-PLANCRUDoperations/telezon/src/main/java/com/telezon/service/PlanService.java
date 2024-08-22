package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.PlanDao;
import com.telezon.model.Plan;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlanService {

    @Autowired
    private PlanDao planDao;

    public void addPlan(Plan plan) {
        planDao.save(plan);
    }

    public List<Plan> getPlans() {
        return planDao.findAll();
    }

    public Plan getPlanById(Integer pid) {
        return planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    public Plan updatePlan(Integer pid, Plan plan) {
        Plan existingPlan = planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
        existingPlan.setPname(plan.getPname());
        existingPlan.setDuration(plan.getDuration());
        return planDao.save(existingPlan);
    }

    public void deletePlan(Integer pid) {
        Plan plan = planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
        planDao.delete(plan);
    }
}
