package com.ibm.genapp.repository;

import com.ibm.genapp.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Claim entity
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    
    List<Claim> findByPolicyPolicyNumber(Long policyNumber);
}

// Made with Bob
