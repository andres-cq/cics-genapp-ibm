package com.ibm.genapp.controller;

import com.ibm.genapp.model.*;
import com.ibm.genapp.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for Policy operations
 */
@RestController
@RequestMapping("/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicyById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Policy>> getPoliciesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(policyService.getPoliciesByCustomerId(customerId));
    }

    @GetMapping("/type/{policyType}")
    public ResponseEntity<List<Policy>> getPoliciesByType(@PathVariable String policyType) {
        return ResponseEntity.ok(policyService.getPoliciesByType(policyType));
    }

    @PostMapping("/motor")
    public ResponseEntity<Policy> createMotorPolicy(@RequestBody Map<String, Object> request) {
        Long customerId = Long.valueOf(request.get("customerId").toString());
        Policy policy = mapToPolicy(request);
        Motor motor = mapToMotor(request);
        
        Policy created = policyService.createMotorPolicy(customerId, policy, motor);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/endowment")
    public ResponseEntity<Policy> createEndowmentPolicy(@RequestBody Map<String, Object> request) {
        Long customerId = Long.valueOf(request.get("customerId").toString());
        Policy policy = mapToPolicy(request);
        Endowment endowment = mapToEndowment(request);
        
        Policy created = policyService.createEndowmentPolicy(customerId, policy, endowment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/house")
    public ResponseEntity<Policy> createHousePolicy(@RequestBody Map<String, Object> request) {
        Long customerId = Long.valueOf(request.get("customerId").toString());
        Policy policy = mapToPolicy(request);
        House house = mapToHouse(request);
        
        Policy created = policyService.createHousePolicy(customerId, policy, house);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/commercial")
    public ResponseEntity<Policy> createCommercialPolicy(@RequestBody Map<String, Object> request) {
        Long customerId = Long.valueOf(request.get("customerId").toString());
        Policy policy = mapToPolicy(request);
        Commercial commercial = mapToCommercial(request);
        
        Policy created = policyService.createCommercialPolicy(customerId, policy, commercial);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(
            @PathVariable Long id,
            @Valid @RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.updatePolicy(id, policy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }

    // Helper methods to map request data to entities
    private Policy mapToPolicy(Map<String, Object> request) {
        Policy policy = new Policy();
        // Map common policy fields from request
        return policy;
    }

    private Motor mapToMotor(Map<String, Object> request) {
        Motor motor = new Motor();
        // Map motor-specific fields from request
        return motor;
    }

    private Endowment mapToEndowment(Map<String, Object> request) {
        Endowment endowment = new Endowment();
        // Map endowment-specific fields from request
        return endowment;
    }

    private House mapToHouse(Map<String, Object> request) {
        House house = new House();
        // Map house-specific fields from request
        return house;
    }

    private Commercial mapToCommercial(Map<String, Object> request) {
        Commercial commercial = new Commercial();
        // Map commercial-specific fields from request
        return commercial;
    }
}

// Made with Bob
