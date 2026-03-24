package com.ibm.genapp.service;

import com.ibm.genapp.exception.ResourceNotFoundException;
import com.ibm.genapp.model.Claim;
import com.ibm.genapp.model.Policy;
import com.ibm.genapp.repository.ClaimRepository;
import com.ibm.genapp.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Claim business logic
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
    }

    public List<Claim> getClaimsByPolicyId(Long policyId) {
        return claimRepository.findByPolicyPolicyNumber(policyId);
    }

    public Claim createClaim(Long policyId, Claim claim) {
        Policy policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new ResourceNotFoundException("Policy", "id", policyId));
        
        claim.setPolicy(policy);
        return claimRepository.save(claim);
    }

    public Claim updateClaim(Long id, Claim claimDetails) {
        Claim claim = getClaimById(id);
        
        claim.setClaimDate(claimDetails.getClaimDate());
        claim.setPaid(claimDetails.getPaid());
        claim.setValue(claimDetails.getValue());
        claim.setCause(claimDetails.getCause());
        claim.setObservations(claimDetails.getObservations());
        
        return claimRepository.save(claim);
    }

    public void deleteClaim(Long id) {
        Claim claim = getClaimById(id);
        claimRepository.delete(claim);
    }
}

// Made with Bob
