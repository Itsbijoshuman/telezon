package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.PlanDao;
import com.telezon.dao.PrepaidDao;
import com.telezon.dao.PostpaidDao;
import com.telezon.model.Plan;
import com.telezon.model.Prepaid;
import com.telezon.model.Postpaid;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private PrepaidDao prepaidDao;

    @Autowired
    private PostpaidDao postpaidDao;

    public String addPlan(Plan plan) {
        planDao.save(plan);
        return "Plan Added";
    }

    public String addPrepaidPlan(Prepaid prepaid) {
        prepaidDao.save(prepaid);
        return "Prepaid Plan Added";
    }

    public String addPostpaidPlan(Postpaid postpaid) {
        postpaidDao.save(postpaid);
        return "Postpaid Plan Added";
    }

    public List<Plan> getPlans() {
        return planDao.findAll();
    }

    public List<Prepaid> getPrepaidPlans() {
        return prepaidDao.findAll();
    }

    public List<Postpaid> getPostpaidPlans() {
        return postpaidDao.findAll();
    }

    public Plan updatePlan(Integer pid, Plan plan) {
        Plan existingPlan = planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
        existingPlan.setPname(plan.getPname());
        existingPlan.setDuration(plan.getDuration());
        return planDao.save(existingPlan);
    }

    public Prepaid updatePrepaid(Integer planId, Prepaid prepaid) {
        Prepaid existingPrepaid = prepaidDao.findById(planId).orElseThrow(() -> new RuntimeException("Prepaid plan not found"));
        existingPrepaid.setPname(prepaid.getPname());
        existingPrepaid.setPlanPrice(prepaid.getPlanPrice());
        existingPrepaid.setDuration(prepaid.getDuration());
        existingPrepaid.setPlanLimit(prepaid.getPlanLimit());
        return prepaidDao.save(existingPrepaid);
    }

    public Postpaid updatePostpaid(Integer planId, Postpaid postpaid) {
        Postpaid existingPostpaid = postpaidDao.findById(planId).orElseThrow(() -> new RuntimeException("Postpaid plan not found"));
        existingPostpaid.setPlanName(postpaid.getPlanName());
        existingPostpaid.setPlanPrice(postpaid.getPlanPrice());
        existingPostpaid.setPlanBillCycle(postpaid.getPlanBillCycle());
        existingPostpaid.setPlanDataCap(postpaid.getPlanDataCap());
        return postpaidDao.save(existingPostpaid);
    }

    public void deletePlan(Integer pid) {
        Plan plan = planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
        planDao.delete(plan);
    }

    public void deletePrepaidPlan(Integer planId) {
        Prepaid prepaid = prepaidDao.findById(planId).orElseThrow(() -> new RuntimeException("Prepaid plan not found"));
        prepaidDao.delete(prepaid);
    }

    public void deletePostpaidPlan(Integer planId) {
        Postpaid postpaid = postpaidDao.findById(planId).orElseThrow(() -> new RuntimeException("Postpaid plan not found"));
        postpaidDao.delete(postpaid);
    }
}
