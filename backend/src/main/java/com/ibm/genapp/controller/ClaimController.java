package com.ibm.genapp.controller;

import com.ibm.genapp.model.Claim;
import com.ibm.genapp.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Claim operations
 */
@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @GetMapping("/policy/{policyId}")
    public ResponseEntity<List<Claim>> getClaimsByPolicy(@PathVariable Long policyId) {
        return ResponseEntity.ok(claimService.getClaimsByPolicyId(policyId));
    }

    @PostMapping("/policy/{policyId}")
    public ResponseEntity<Claim> createClaim(
            @PathVariable Long policyId,
            @Valid @RequestBody Claim claim) {
        Claim created = claimService.createClaim(policyId, claim);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(
            @PathVariable Long id,
            @Valid @RequestBody Claim claim) {
        return ResponseEntity.ok(claimService.updateClaim(id, claim));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}

// Made with Bob
