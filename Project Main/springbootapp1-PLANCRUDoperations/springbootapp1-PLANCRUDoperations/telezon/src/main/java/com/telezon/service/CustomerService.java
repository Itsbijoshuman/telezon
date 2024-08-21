package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.CustomerDao;
import com.telezon.dao.PrepaidDao;
import com.telezon.dao.PostpaidDao;
import com.telezon.model.Customer;
import com.telezon.model.Prepaid;
import com.telezon.model.Postpaid;

import jakarta.transaction.Transactional;
import java.util.List;

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
        return "Customer Added";
    }

    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    public Customer getCustomerById(Integer customerId) {
        return customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Integer customerId, Customer customer) {
        Customer existingCustomer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setPrepaidPlan(customer.getPrepaidPlan());
        existingCustomer.setPostpaidPlan(customer.getPostpaidPlan());
        return customerDao.save(existingCustomer);
    }

    public void deleteCustomer(Integer customerId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customerDao.delete(customer);
    }

    public List<Prepaid> getPrepaidPlans() {
        return prepaidDao.findAll();
    }

    public List<Postpaid> getPostpaidPlans() {
        return postpaidDao.findAll();
    }
}
