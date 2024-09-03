package com.telezon.service;

import com.telezon.dao.CustomerDao;
import com.telezon.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTestCase {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testAddCustomer() {
        Customer customer = new Customer();
        customerService.addCustomer(customer);

        verify(customerDao, times(1)).save(customer);
    }

    @Test
    void testGetCustomers() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        when(customerDao.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        assertEquals(2, customerService.getCustomers().size());
    }

    @Test
    void testGetPrepaidCustomers() {
        Customer customer = new Customer();
        when(customerDao.findByPrepaidPlanIsNotNull()).thenReturn(Arrays.asList(customer));

        assertEquals(1, customerService.getPrepaidCustomers().size());
    }

    @Test
    void testGetPostpaidCustomers() {
        Customer customer = new Customer();
        when(customerDao.findByPostpaidPlanIsNotNull()).thenReturn(Arrays.asList(customer));

        assertEquals(1, customerService.getPostpaidCustomers().size());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        when(customerDao.findById(1)).thenReturn(Optional.of(customer));

        assertNotNull(customerService.getCustomerById(1));
    }

    @Test
    void testUpdateCustomer() {
        Customer existingCustomer = new Customer();
        Customer updatedCustomer = new Customer();
        when(customerDao.findById(1)).thenReturn(Optional.of(existingCustomer));
        when(customerDao.save(existingCustomer)).thenReturn(existingCustomer);

        Customer result = customerService.updateCustomer(1, updatedCustomer);
        assertNotNull(result);
        verify(customerDao, times(1)).save(existingCustomer);
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer();
        when(customerDao.findById(1)).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(1);
        verify(customerDao, times(1)).delete(customer);
    }

    @Test
    void testUpdateChargesFromCall() {
        Customer customer = new Customer();
        customer.setCharges(10.0);
        when(customerDao.findByName("John Doe")).thenReturn(customer);
        when(customerDao.save(customer)).thenReturn(customer);

        customerService.updateChargesFromCall("John Doe", 5.0);
        assertEquals(15.0, customer.getCharges());
        verify(customerDao, times(1)).save(customer);
    }
}
