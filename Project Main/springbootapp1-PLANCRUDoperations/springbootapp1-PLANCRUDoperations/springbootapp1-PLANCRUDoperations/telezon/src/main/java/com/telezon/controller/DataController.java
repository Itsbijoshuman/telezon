	package com.telezon.controller;

import com.telezon.model.Data;
import com.telezon.model.Customer;
import com.telezon.model.Postpaid;
import com.telezon.service.DataService;
import com.telezon.service.CustomerService;
import com.telezon.service.PostpaidService;
import com.telezon.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PostpaidService postpaidService;

    @Autowired
    private CustomerDao customerDao;

    @GetMapping
    public String listData(Model model) {
        List<Customer> customers = customerDao.findAll();
        List<Data> dataList = dataService.getAllData();
        model.addAttribute("dataList", dataList);
        model.addAttribute("data", new Data());
        model.addAttribute("customers", customers);
        return "data";
    }

    @PostMapping
    public String addData(@ModelAttribute Data data) {
        // Save the initial data details
        dataService.saveData(data);

        // Fetch the customer based on the dropdown selection
        List<Customer> customers = customerService.getCustomers();
        Optional<Customer> customerOpt = customers.stream()
                .filter(c -> c.getName().equals(data.getCustomer().getName()))
                .findFirst();

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            // Handle Prepaid Customer (Subtract from remaining data)
            if (customer.getPrepaidPlan() != null) {
                double newRemainingData = customer.getRemainingData() - data.getUsedData();
                customer.setRemainingData(newRemainingData);
                customerService.updateCustomer(customer.getId(), customer);
            }
            // Handle Postpaid Customer (Multiply used data by plan price)
            else if (customer.getPostpaidPlan() != null) {
                Integer postpaidPlanId = customer.getPostpaidPlan();
                Postpaid postpaidPlan = postpaidService.getPostpaidPlans().stream()
                        .filter(p -> p.getPlanId().equals(postpaidPlanId))
                        .findFirst()
                        .orElse(null);

                if (postpaidPlan != null) {
                    // Calculate charge based on used data and plan price
                    double charge = postpaidPlan.getPlanPrice() * data.getUsedData();
                	data.setInstanceCharge(charge);
                    customerService.updateChargesFromData(customer.getName(), charge);
                    dataService.updateData(data);
                    
                }
            }
        }

        return "redirect:/data";
    }

    @GetMapping("/{id}")
    public String editData(@PathVariable Integer id, Model model) {
        Optional<Data> data = dataService.getDataById(id);
        List<Customer> customers = customerService.getCustomers();
        if (data.isPresent()) {
            model.addAttribute("data", data.get());
            model.addAttribute("customers", customers); // Add customer list for editing
            return "data"; 
        } else {
            return "redirect:/data"; 
        }
    }

    @PostMapping("/{id}")
    public String updateData(@PathVariable Integer id, @ModelAttribute Data data) {
        if (dataService.getDataById(id).isPresent()) {
            data.setId(id);
            dataService.updateData(data);
        }
        return "redirect:/data"; 
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Integer id) {
        dataService.deleteData(id);
        return "redirect:/data"; 
    }
}