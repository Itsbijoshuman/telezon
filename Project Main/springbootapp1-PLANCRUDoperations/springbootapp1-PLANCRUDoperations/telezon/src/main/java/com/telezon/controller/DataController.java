package com.telezon.controller;

import com.telezon.model.Data;
import com.telezon.model.Call;
import com.telezon.model.Customer;
import com.telezon.service.CustomerService;
import com.telezon.service.DataService;
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

    @GetMapping
    public String listData(Model model) {
        List<Data> dataList = dataService.getAllData();
        List<Customer> customerList = customerService.getCustomers();
        model.addAttribute("dataList", dataList);
        model.addAttribute("data", new Data()); // Prepare an empty Data object for form binding
        model.addAttribute("customerList", customerList); // Add customer list to model
        return "data"; // Refers to data.html
    }
    
    @PostMapping
    public String addData(@ModelAttribute Data data) {
        // Retrieve the customer associated with the data
        Optional<Customer> customerOpt = customerService.getCustomers().stream()
                .filter(c -> c.getId().equals(data.getCustomer().getId())) // Match customer by ID
                .findFirst();

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            // Set the totalData field based on the customer's remainingData
            data.setTotalData(customer.getRemainingData());

            // Update the customer's remainingData after using some of it
            double newData = customer.getRemainingData() - data.getUsedData();
            customer.setRemainingData(newData);

            // Save the data
            dataService.saveData(data);

            // Update the customer in the database
            customerService.updateCustomer(customer.getId(), customer);
        }
        
        return "redirect:/data"; // Redirect to the data list page
    }


    @GetMapping("/{id}")
    public String editData(@PathVariable Integer id, Model model) {
        Optional<Data> data = dataService.getDataById(id);
        List<Customer> customerList = customerService.getCustomers();
        if (data.isPresent()) {
            model.addAttribute("data", data.get());
            model.addAttribute("customerList", customerList); // Add customer list for editing
            return "data"; // Refers to data.html for editing
        } else {
            return "redirect:/data"; // Redirect if the data is not found
        }
    }

    @PostMapping("/{id}")
    public String updateData(@PathVariable Integer id, @ModelAttribute Data data) {
        if (dataService.getDataById(id).isPresent()) {
            data.setId(id);
            dataService.updateData(data);
        }
        return "redirect:/data"; // Redirect to /data URL
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Integer id) {
        dataService.deleteData(id);
        return "redirect:/data"; // Redirect to /data URL
    }
}
