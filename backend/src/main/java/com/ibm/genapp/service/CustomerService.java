package com.ibm.genapp.service;

import com.ibm.genapp.exception.ResourceNotFoundException;
import com.ibm.genapp.model.Customer;
import com.ibm.genapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Customer business logic
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    public List<Customer> searchCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setDateOfBirth(customerDetails.getDateOfBirth());
        customer.setHouseName(customerDetails.getHouseName());
        customer.setHouseNumber(customerDetails.getHouseNumber());
        customer.setPostcode(customerDetails.getPostcode());
        customer.setPhoneHome(customerDetails.getPhoneHome());
        customer.setPhoneMobile(customerDetails.getPhoneMobile());
        customer.setEmailAddress(customerDetails.getEmailAddress());
        
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}

// Made with Bob
