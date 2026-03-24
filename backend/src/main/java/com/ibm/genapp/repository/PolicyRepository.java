package com.ibm.genapp.repository;

import com.ibm.genapp.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Policy entity
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    
    List<Policy> findByCustomerCustomerNumber(Long customerNumber);
    
    List<Policy> findByPolicyType(String policyType);
    
    @Query("SELECT p FROM Policy p WHERE p.customer.customerNumber = :customerNumber AND p.policyType = :policyType")
    List<Policy> findByCustomerAndType(@Param("customerNumber") Long customerNumber, 
                                       @Param("policyType") String policyType);
}

// Made with Bob
