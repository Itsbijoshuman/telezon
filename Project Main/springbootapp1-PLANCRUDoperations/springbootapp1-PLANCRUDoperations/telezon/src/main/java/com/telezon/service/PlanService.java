package com.telezon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telezon.dao.PlanDao;
import com.telezon.dao.PrepaidDao;
import com.telezon.model.Plan;
import com.telezon.model.Prepaid;

import jakarta.transaction.Transactional;
@Service//@Component
@Transactional
public class PlanService {
	@Autowired
	PlanDao planDao;
	public String addPlan(Plan plan) {
		planDao.save(plan);
		return "Plan Type Added";
	}
	
	@Autowired
	PrepaidDao prepaidDao;
	public String addPrepaidPlan(Prepaid prepaid)
	{
		prepaidDao.save(prepaid);
		return "Prepaid Plan Added";
	}
	public   List<Plan> getPlans() {
		List<Plan>  planList=planDao.findAll();
		return planList;
	}
	
	public   List<Prepaid> getPrepaidPlans() {
		List<Prepaid>  prepaidList=prepaidDao.findAll();
		return prepaidList;
	}
	
	public Plan updatePlan(Integer pid,Plan plan) {
		Plan plan1=planDao.findById(pid).get();
		//plan1.setDuration(plan.getDuration());
		//return planDao.findById(pid).get();
		
		plan1.setPname(plan.getPname());
		return planDao.findById(pid).get();
	}
	
	public Prepaid updatePrepaidPrice(Integer plan_id,Prepaid prepaid)
	{
		Prepaid prepaid1=prepaidDao.findById(plan_id).get();
		prepaid1.setPlanPrice(prepaid.getPlanPrice());
		return prepaidDao.findById(plan_id).get();
	}
	
	public Plan deletePlan(Integer pid) {
		Plan plan1=planDao.findById(pid).get();
		if(plan1!=null)
		planDao.deleteById(pid);
		return plan1;
		}
	
	}


