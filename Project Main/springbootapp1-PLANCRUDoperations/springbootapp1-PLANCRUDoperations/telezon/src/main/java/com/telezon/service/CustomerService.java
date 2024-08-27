package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.CustomerDao;
import com.telezon.model.Customer;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void addCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerDao.findAll();
    }

    public List<Customer> getPrepaidCustomers() {
        return customerDao.findByPrepaidPlanIsNotNull();
    }

    public List<Customer> getPostpaidCustomers() {
        return customerDao.findByPostpaidPlanIsNotNull();
    }

    public Customer getCustomerById(Integer customerId) {
        return customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Integer customerId, Customer customer) {
        Customer existingCustomer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        return customerDao.save(existingCustomer);
    } 	

    public void deleteCustomer(Integer customerId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customerDao.delete(customer);
    }
}
