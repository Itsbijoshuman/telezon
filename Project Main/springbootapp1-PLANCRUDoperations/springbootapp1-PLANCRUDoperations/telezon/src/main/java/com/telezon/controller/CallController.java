package com.telezon.controller;

import com.telezon.model.Call;
import com.telezon.model.Customer;
import com.telezon.model.Postpaid;
import com.telezon.service.CallService;
import com.telezon.service.CustomerService;
import com.telezon.service.PostpaidService;
import com.telezon.dao.CallDao;
import com.telezon.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/calls")
public class CallController {

	@Autowired
	private CallDao callDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CallService callService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PostpaidService postpaidService;

	@GetMapping
	public String listCalls(Model model) {
		List<Customer> customers = customerDao.findAll();

		List<Call> calls = callService.getAllCalls();
		model.addAttribute("calls", calls);
		model.addAttribute("call", new Call());
		model.addAttribute("customers", customers);
		return "call";
	}

	@PostMapping
	public String addCall(@ModelAttribute Call call) {
	    // Save the initial call details
	    callService.saveCall(call);

	    // Fetch the customer based on the 'fromName' dropdown selection
	    List<Customer> customers = customerService.getCustomers();
	    Optional<Customer> customerOpt = customers.stream()
	                                              .filter(c -> c.getName().equals(call.getFromName()))
	                                              .findFirst();

	    if (customerOpt.isPresent()) {
	        Customer customer = customerOpt.get();

	        // Handle Prepaid Customer (Existing logic, no changes)
	        if (customer.getPrepaidPlan() != null) {
	            double newBalance = customer.getRemainingBalance() - call.getUsedDuration();
	            customer.setRemainingBalance(newBalance);
	            customerService.updateCustomer(customer.getId(), customer);
	        }
	        // Handle Postpaid Customer (New logic)
	        else if (customer.getPostpaidPlan() != null) {
	            // Fetch the corresponding Postpaid Plan using the PostpaidService
	            Integer postpaidPlanId = customer.getPostpaidPlan();
	            Postpaid postpaidPlan = postpaidService.getPostpaidPlans().stream()
	                    .filter(p -> p.getPlanId().equals(postpaidPlanId))
	                    .findFirst()
	                    .orElse(null);

	            if (postpaidPlan != null) {
	                // Calculate instance charge based on used duration and plan price
	                double instanceCharge = postpaidPlan.getPlanPrice() * call.getUsedDuration();
	                call.setInstanceCharge(instanceCharge);
	                

	                // Save the updated call with the instance charge
	                customerService.updateChargesFromCall(call.getFromName(), call.getInstanceCharge());
	                callService.updateCall(call);
	            }
	        }
	    }

	    return "redirect:/calls";
	}


	@GetMapping("/{id}")
	public String editCall(@PathVariable Integer id, Model model) {
		Optional<Call> call = callService.getCallById(id);
		if (call.isPresent()) {
			model.addAttribute("call", call.get());
			return "call"; 
		} else {
			return "redirect:/calls"; 
		}
	}

	@PostMapping("/{id}")
	public String updateCall(@PathVariable Integer id, @ModelAttribute Call call) {
		if (callService.getCallById(id).isPresent()) {
			call.setCid(id);
			callService.updateCall(call);
		}
		return "redirect:/calls"; 
	}

	@DeleteMapping("/{id}")
	public String deleteCall(@PathVariable Integer id) {
		callService.deleteCall(id);
		return "redirect:/calls"; 
	}

	@GetMapping("/calls")
	public String showCallsPage(Model model) {
		List<Customer> customers = customerDao.findAll();
		model.addAttribute("customers", customers);
		return "calls";
	}
}
