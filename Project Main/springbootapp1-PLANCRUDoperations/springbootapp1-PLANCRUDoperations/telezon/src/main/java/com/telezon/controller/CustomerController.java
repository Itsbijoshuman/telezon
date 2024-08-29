package com.telezon.controller;

import com.telezon.model.Customer;

import com.telezon.model.Postpaid;
import com.telezon.model.Prepaid;
import com.telezon.service.CustomerService;

import com.telezon.service.PostpaidService;
import com.telezon.service.PrepaidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PostpaidService postpaidService;

    @Autowired
    private PrepaidService prepaidService;

    @GetMapping
    public String listCustomers(@RequestParam(value = "filter", required = false, defaultValue = "all") String filter, Model model) {
        List<Customer> customers;

        switch (filter) {
            case "prepaid":
                customers = customerService.getPrepaidCustomers();
                break;
            case "postpaid":
                customers = customerService.getPostpaidCustomers();
                break;
            default:
                customers = customerService.getCustomers();
        }

        List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
        List<Prepaid> prepaidPlans = prepaidService.getPrepaidPlans();

        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        model.addAttribute("postpaidPlans", postpaidPlans);
        model.addAttribute("prepaidPlans", prepaidPlans);
        return "customers";
    }

    @PostMapping
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        if (customer.getPrepaidPlan() != null) {
        	Prepaid selectedPrepaidPlan = prepaidService.getPrepaidPlanById(customer.getPrepaidPlan());
            customer.setRemainingBalance((double) selectedPrepaidPlan.getDuration());
            customer.setPostpaidPlan(null);
        } else if (customer.getPostpaidPlan() != null) {
            customer.setPrepaidPlan(null);
            customer.setRemainingBalance(null);
        }

        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customers";
    }

    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Integer id, @ModelAttribute Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
