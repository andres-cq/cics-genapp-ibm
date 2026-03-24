package com.ibm.genapp.repository;

import com.ibm.genapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Customer entity
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);
    
    List<Customer> findByPostcode(String postcode);
    
    List<Customer> findByEmailAddress(String emailAddress);
}

// Made with Bob
