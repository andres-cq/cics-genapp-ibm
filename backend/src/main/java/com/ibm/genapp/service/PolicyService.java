package com.ibm.genapp.service;

import com.ibm.genapp.exception.ResourceNotFoundException;
import com.ibm.genapp.model.*;
import com.ibm.genapp.repository.CustomerRepository;
import com.ibm.genapp.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Policy business logic
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Policy", "id", id));
    }

    public List<Policy> getPoliciesByCustomerId(Long customerId) {
        return policyRepository.findByCustomerCustomerNumber(customerId);
    }

    public List<Policy> getPoliciesByType(String policyType) {
        return policyRepository.findByPolicyType(policyType);
    }

    public Policy createMotorPolicy(Long customerId, Policy policy, Motor motor) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        
        policy.setCustomer(customer);
        policy.setPolicyType("M");
        Policy savedPolicy = policyRepository.save(policy);
        
        motor.setPolicy(savedPolicy);
        motor.setPolicyNumber(savedPolicy.getPolicyNumber());
        savedPolicy.setMotor(motor);
        
        return policyRepository.save(savedPolicy);
    }

    public Policy createEndowmentPolicy(Long customerId, Policy policy, Endowment endowment) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        
        policy.setCustomer(customer);
        policy.setPolicyType("E");
        Policy savedPolicy = policyRepository.save(policy);
        
        endowment.setPolicy(savedPolicy);
        endowment.setPolicyNumber(savedPolicy.getPolicyNumber());
        savedPolicy.setEndowment(endowment);
        
        return policyRepository.save(savedPolicy);
    }

    public Policy createHousePolicy(Long customerId, Policy policy, House house) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        
        policy.setCustomer(customer);
        policy.setPolicyType("H");
        Policy savedPolicy = policyRepository.save(policy);
        
        house.setPolicy(savedPolicy);
        house.setPolicyNumber(savedPolicy.getPolicyNumber());
        savedPolicy.setHouse(house);
        
        return policyRepository.save(savedPolicy);
    }

    public Policy createCommercialPolicy(Long customerId, Policy policy, Commercial commercial) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        
        policy.setCustomer(customer);
        policy.setPolicyType("C");
        Policy savedPolicy = policyRepository.save(policy);
        
        commercial.setPolicy(savedPolicy);
        commercial.setPolicyNumber(savedPolicy.getPolicyNumber());
        savedPolicy.setCommercial(commercial);
        
        return policyRepository.save(savedPolicy);
    }

    public Policy updatePolicy(Long id, Policy policyDetails) {
        Policy policy = getPolicyById(id);
        
        policy.setIssueDate(policyDetails.getIssueDate());
        policy.setExpiryDate(policyDetails.getExpiryDate());
        policy.setBrokerId(policyDetails.getBrokerId());
        policy.setBrokersReference(policyDetails.getBrokersReference());
        policy.setPayment(policyDetails.getPayment());
        policy.setCommission(policyDetails.getCommission());
        
        return policyRepository.save(policy);
    }

    public void deletePolicy(Long id) {
        Policy policy = getPolicyById(id);
        policyRepository.delete(policy);
    }
}

// Made with Bob
