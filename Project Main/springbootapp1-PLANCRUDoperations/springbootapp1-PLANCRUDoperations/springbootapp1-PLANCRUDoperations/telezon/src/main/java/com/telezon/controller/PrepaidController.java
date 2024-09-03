package com.telezon.controller;

import com.telezon.model.Prepaid;
import com.telezon.service.PrepaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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
    public String addPrepaidPlan(@ModelAttribute("prepaid") @Valid Prepaid prepaid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Prepaid> prepaidPlans = prepaidService.getPrepaidPlans();
            model.addAttribute("prepaidPlans", prepaidPlans);
            return "prepaid"; // Return to the form view with errors
        }

        try {
            // Validate Prepaid entity
            if (prepaid.getPlanId() == null || prepaid.getPlanId() <= 0) {
                throw new IllegalArgumentException("Invalid Plan ID provided. Plan ID must be non-null and positive.");
            }

            prepaidService.addPrepaidPlan(prepaid);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            List<Prepaid> prepaidPlans = prepaidService.getPrepaidPlans();
            model.addAttribute("prepaidPlans", prepaidPlans);
            return "prepaid"; // Return to the form view with validation error
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while adding the prepaid plan");
            List<Prepaid> prepaidPlans = prepaidService.getPrepaidPlans();
            model.addAttribute("prepaidPlans", prepaidPlans);
            return "prepaid"; // Return to the form view with general error
        }

        return "redirect:/prepaid";
    }

    @GetMapping("/{id}")
    public Prepaid getPrepaidPlan(@PathVariable Integer id) {
        return prepaidService.getPrepaidPlanById(id);
    }
}
