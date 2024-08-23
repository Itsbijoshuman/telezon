package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.CustomerDao;
import com.telezon.model.Customer;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public void addCustomer(Customer customer) {
		if (customer.getPrepaidPlan() != null) {
			customer.setPostpaidPlan(null);
		} else if (customer.getPostpaidPlan() != null) {
			customer.setPrepaidPlan(null);
		}
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
		Customer existingCustomer = customerDao.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		existingCustomer.setName(customer.getName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPhoneNumber(customer.getPhoneNumber());

		if (customer.getPrepaidPlan() != null) {
			existingCustomer.setPrepaidPlan(customer.getPrepaidPlan());
			existingCustomer.setPostpaidPlan(null);
		} else if (customer.getPostpaidPlan() != null) {
			existingCustomer.setPostpaidPlan(customer.getPostpaidPlan());
			existingCustomer.setPrepaidPlan(null);
		}

		return customerDao.save(existingCustomer);
	}

	public void deleteCustomer(Integer customerId) {
		Optional<Customer> customerOptional = customerDao.findById(customerId);
		if (customerOptional.isPresent()) {
			// Clear any associated plans if necessary (if using JPA cascading or specific
			// business logic)
			Customer customer = customerOptional.get();
			customer.setPrepaidPlan(null);
			customer.setPostpaidPlan(null);

			// Delete the customer record
			customerDao.delete(customer); // Ensure this method call is used
		}
	}
}
