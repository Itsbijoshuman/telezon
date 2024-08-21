package com.telezon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.PlanDao;
import com.telezon.dao.PostpaidDao;
import com.telezon.dao.PrepaidDao;
import com.telezon.model.Plan;
import com.telezon.model.Postpaid;
import com.telezon.model.Prepaid;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlanService {

    @Autowired
    PlanDao planDao;

    @Autowired
    PrepaidDao prepaidDao;
    
    PostpaidDao postpaidDao;

    public String addPlan(Plan plan) {
        planDao.save(plan);
        return "Plan Type Added";
    }

    public String addPrepaidPlan(Prepaid prepaid) {
        prepaidDao.save(prepaid);
        return "Prepaid Plan Added";
    }
    
    public String addPostpaidPlan(Postpaid postpaid)
    {
    	postpaidDao.save(postpaid);
    	return "Postpaid Plan Added";
    }

    public List<Plan> getPlans() {
        return planDao.findAll();
    }

    public List<Prepaid> getPrepaidPlans() {
        return prepaidDao.findAll();
    }
    
    public List<Postpaid> getPostpaidPlans()
    {
    	return postpaidDao.findAll();
    }

    public Plan updatePlan(Integer pid, Plan plan) {
        Plan plan1 = planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
        plan1.setPname(plan.getPname());
        return planDao.save(plan1);
    }

    public Prepaid updatePrepaid(Integer plan_id, Prepaid updatedPrepaid) {
        Prepaid existingPrepaid = prepaidDao.findById(plan_id).orElseThrow(() -> new RuntimeException("Prepaid Plan not found"));
        existingPrepaid.setPlanPrice(updatedPrepaid.getPlanPrice());
        existingPrepaid.setPlanLimit(updatedPrepaid.getPlanLimit());
        return prepaidDao.save(existingPrepaid);
    }
    
    public Postpaid updatePostpaid(Integer plan_id,Postpaid updatedPostpaid)
    {
    	Postpaid existingPostpaid = postpaidDao.findById(plan_id).orElseThrow(()-> new RuntimeException("Postpaid plan not found"));
    	existingPostpaid.setPlanPrice(updatedPostpaid.getPlanPrice());
    	existingPostpaid.setPlanDataCap(updatedPostpaid.getPlanDataCap());
    	existingPostpaid.setPlanBillCycle(updatedPostpaid.getPlanBillCycle());
    	return postpaidDao.save(existingPostpaid);
    }
    
    
    
    public Prepaid deletePrepaidPlan(Integer plan_id) {
        Prepaid prepaid1 = prepaidDao.findById(plan_id).orElseThrow(() -> new RuntimeException("Prepaid Plan not found"));
        prepaidDao.deleteById(plan_id);
        return prepaid1;

    }

    public Plan deletePlan(Integer pid) {
        Plan plan1 = planDao.findById(pid).orElseThrow(() -> new RuntimeException("Plan not found"));
        planDao.deleteById(pid);
        return plan1;
    }
    
    public Postpaid deletePostpaidPlan(Integer plan_id)
    {
    	Postpaid postpaid1 = postpaidDao.findById(plan_id).orElseThrow(() -> new RuntimeException("Plan not Found"));
    	postpaidDao.deleteById(plan_id);
		return postpaid1;
    	
    }
}
