package com.telezon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.telezon.model.Customer;
import com.telezon.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @PostMapping
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer";
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
