package com.telezon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telezon.dao.CustomerDao;
import com.telezon.dao.PostpaidDao;
import com.telezon.dao.PrepaidDao;
import com.telezon.model.Customer;	
import com.telezon.model.Postpaid;
import com.telezon.model.Prepaid;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PrepaidDao prepaidDao;

    @Autowired
    private PostpaidDao postpaidDao;

    public String addCustomer(Customer customer) {
        customerDao.save(customer);
        return "Customer Added Successfully";
    }

    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerDao.findById(customerId);
    }

    public String updateCustomer(Integer customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customerDao.save(existingCustomer);
        return "Customer Updated Successfully";
    }

    public String assignPrepaidPlanToCustomer(Integer customerId, Integer prepaidPlanId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Prepaid prepaidPlan = prepaidDao.findById(prepaidPlanId).orElseThrow(() -> new RuntimeException("Prepaid plan not found"));
        customer.setPrepaidPlan(prepaidPlan);
        customer.setPostpaidPlan(null);  // Ensures only one type of plan is assigned
        customerDao.save(customer);
        return "Prepaid Plan Assigned to Customer";
    }

    public String assignPostpaidPlanToCustomer(Integer customerId, Integer postpaidPlanId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Postpaid postpaidPlan = postpaidDao.findById(postpaidPlanId).orElseThrow(() -> new RuntimeException("Postpaid plan not found"));
        customer.setPostpaidPlan(postpaidPlan);
        customer.setPrepaidPlan(null);  // Ensures only one type of plan is assigned
        customerDao.save(customer);
        return "Postpaid Plan Assigned to Customer";
    }

    public String deleteCustomer(Integer customerId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customerDao.delete(customer);
        return "Customer Deleted Successfully";
    }
}
