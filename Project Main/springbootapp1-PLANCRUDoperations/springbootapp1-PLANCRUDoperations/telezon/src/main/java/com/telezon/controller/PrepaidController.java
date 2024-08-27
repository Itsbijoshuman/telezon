package com.telezon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.telezon.model.Prepaid;
import com.telezon.service.PrepaidService;

import java.util.List;

@Controller
@RequestMapping("/prepaid")
public class PrepaidController {

    @Autowired
    private PrepaidService prepaidService;

    @GetMapping
    public String listPrepaidPlans(Model model) {
        List<Prepaid> prepaidPlans = prepaidService.getPrepaidPlans();
        model.addAttribute("prepaidPlans", prepaidPlans);
        model.addAttribute("prepaid", new Prepaid());
        return "prepaid";
    }

    @PostMapping
    public String addPrepaidPlan(@ModelAttribute("prepaid") Prepaid prepaid) {
        prepaidService.addPrepaidPlan(prepaid);
        return "redirect:/prepaid";
    }
    
    @GetMapping("/{id}")
    public Prepaid getPrepaidPlan(@PathVariable Integer id) {
        return prepaidService.getPrepaidPlanById(id);
    }
}
