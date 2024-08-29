package com.telezon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.telezon.model.Postpaid;
import com.telezon.service.PostpaidService;

import java.util.List;

@Controller
@RequestMapping("/postpaid")
public class PostpaidController {

    @Autowired
    private PostpaidService postpaidService;

    @GetMapping
    public String listPostpaidPlans(Model model) {
        List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
        model.addAttribute("postpaidPlans", postpaidPlans);
        model.addAttribute("postpaid", new Postpaid());
        return "postpaid";
    }

    @PostMapping
    public String addPostpaidPlan(@ModelAttribute("postpaid") Postpaid postpaid) {
        postpaidService.addPostpaidPlan(postpaid);
        return "redirect:/postpaid";
    }
}
