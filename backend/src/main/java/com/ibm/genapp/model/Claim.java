package com.ibm.genapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Claim entity representing insurance claims
 */
@Entity
@Table(name = "claim")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_number")
    private Long claimNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_number", nullable = false)
    @NotNull
    @JsonBackReference("policy-claims")
    private Policy policy;

    @Column(name = "claim_date")
    private LocalDate claimDate;

    @Column(name = "paid")
    private Integer paid;

    @Column(name = "value")
    private Integer value;

    @Column(name = "cause", length = 255)
    private String cause;

    @Column(name = "observations", length = 255)
    private String observations;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// Made with Bob
