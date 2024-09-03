package com.telezon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.telezon.model.Plan;
import com.telezon.service.PlanService;

import java.util.List;

@Controller
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;
    
    @GetMapping
    public String listPlans(Model model) {
        List<Plan> plans = planService.getPlans();
        model.addAttribute("plans", plans);
        model.addAttribute("plan", new Plan());
        return "plans";
    }

    @PostMapping
    public String addPlan(@ModelAttribute("plan") Plan plan) {
        planService.addPlan(plan);
        return "redirect:/plans";
    }

    @GetMapping("/{id}")
    public String getPlanById(@PathVariable Integer id, Model model) {
        Plan plan = planService.getPlanById(id);
        model.addAttribute("plan", plan);
        return "plans";
    }

    @PostMapping("/{id}")
    public String updatePlan(@PathVariable Integer id, @ModelAttribute Plan plan) {
        planService.updatePlan(id, plan);
        return "redirect:/plans";
    }

    @DeleteMapping("/{id}")
    public String deletePlan(@PathVariable Integer id) {
        planService.deletePlan(id);
        return "redirect:/plans";
    }
}
