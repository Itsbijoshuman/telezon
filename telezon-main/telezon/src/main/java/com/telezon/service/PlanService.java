package com.telezon.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telezon.dao.PlanDao;
import com.telezon.model.Plan;

import jakarta.transaction.Transactional;
@Service//@Component
@Transactional
public class PlanService {
	@Autowired
	PlanDao planDao;
	public String addPlan(Plan plan) {
		planDao.save(plan);
		return "Added";
	}
	public   List<Plan> getPlans() {
		List<Plan>  planList=planDao.findAll();
		return planList;
	}
	public Plan updatePlan(Integer pid,Plan plan) {
		Plan plan1=planDao.findById(pid).get();
		//plan1.setDuration(plan.getDuration());
		//return planDao.findById(pid).get();
		
		plan1.setPname(plan.getPname());
		return planDao.findById(pid).get();
	}
	public Plan deletePlan(Integer pid) {
		Plan plan1=planDao.findById(pid).get();
		if(plan1!=null)
		planDao.deleteById(pid);
		return plan1;
		}
	}
