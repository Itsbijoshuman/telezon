package com.telezon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.telezon.exception.CustomPostpaidException;
import com.telezon.model.Postpaid;
import com.telezon.service.PostpaidService;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/postpaid")
public class PostpaidController {

    @Autowired
    private PostpaidService postpaidService;

    @PostMapping
    public String addPostpaidPlan(@ModelAttribute("postpaid") @Valid Postpaid postpaid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
            model.addAttribute("postpaidPlans", postpaidPlans);
            return "postpaid"; // Ensure this matches the Thymeleaf template name
        }

        try {
            postpaidService.addPostpaidPlan(postpaid);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding Postpaid Plan: " + e.getMessage());
            List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
            model.addAttribute("postpaidPlans", postpaidPlans);
            return "postpaid"; // Return to the same page with the error message
        }

        return "redirect:/postpaid"; // Redirect after successful addition
    }

    @ExceptionHandler(CustomPostpaidException.class)
    public String handleCustomPostpaidException(CustomPostpaidException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
        model.addAttribute("postpaidPlans", postpaidPlans);
        model.addAttribute("postpaid", new Postpaid());
        return "postpaid"; // Renders the postpaid page with error message
    }

    @GetMapping
    public String showPostpaidPage(Model model) {
        List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
        model.addAttribute("postpaidPlans", postpaidPlans);
        model.addAttribute("postpaid", new Postpaid());
        return "postpaid"; // Ensure this matches the Thymeleaf template name
    }
}
