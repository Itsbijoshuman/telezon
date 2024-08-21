package com.telezon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telezon.model.Plan;
import com.telezon.model.Prepaid;
import com.telezon.service.PlanService;
import com.telezon.model.Postpaid;
@RestController
public class PlanController {
	
	@Autowired
	PlanService planService;
	@GetMapping("/plan")
	public List<Plan> getPlanDetails() {
			
		return planService.getPlans();
	}
	@GetMapping("/prepaid")
	public List<Prepaid> getPrepaidDetails(){
		
		return planService.getPrepaidPlans();
	}
	@PostMapping("/prepaid")
	public String addPrepaidDetails(@RequestBody Prepaid prepaid) {
		String result = planService.addPrepaidPlan(prepaid);
		return result;
	}
	@PutMapping("/prepaid/{prepaid}")
	public Prepaid updatePrepaidDetails(@PathVariable("plan_id") Integer plan_id,@RequestBody Prepaid prepaid) {
		return planService.updatePrepaid(plan_id, prepaid);
	}
	@DeleteMapping("/prepaid/{prepaid}")
	public Prepaid deletePrepaidDetails(@PathVariable("plan_id")Integer plan_id) {
		return planService.deletePrepaidPlan(plan_id);
	}
	  @PostMapping("/plan") 
	  public String  addPlanDetails(@RequestBody	  Plan plan) {
		    String result= planService.addPlan(plan);
		  return  result; 
	  }
	
	@PutMapping("/plan/{pid}") 
	public Plan updatePlanDetails(@PathVariable("pid") Integer pid,@RequestBody Plan plan) {
		
		return  planService.updatePlan(pid, plan); 
	}
	
	@DeleteMapping("/plan/{pid}") 
	public Plan deletePlanDetails(@PathVariable("pid") Integer pid) {
		
		return  planService.deletePlan(pid);
	}
	
}
