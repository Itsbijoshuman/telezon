package com.telezon.controller;

import com.telezon.model.Data;
import com.telezon.dao.CustomerDao;
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
    
    @Autowired
    private CustomerDao customerDao;

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
        dataService.saveData(data);
        
        List<Customer> customers = customerService.getCustomers();
        Optional<Customer> customerOpt = customers.stream()
        		.filter(c -> c.getName().equals(data.getCustomer()))
        		.findFirst();
        
        if(customerOpt.isPresent()) {
        	Customer customer = customerOpt.get();
        	
        	double newDataBalance = customer.getRemainingData() - data.getUsedData();
        	customer.setRemainingData(newDataBalance);
        	
        	customerService.updateCustomer(customer.getId(), customer);
        }
        return "redirect:/data"; // Redirects to /data URL
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
	@GetMapping("/data")
	public String showDataPage(Model model) {
		List<Customer> customers = customerDao.findAll();
		model.addAttribute("customers", customers);
		return "calls";
	}
}
