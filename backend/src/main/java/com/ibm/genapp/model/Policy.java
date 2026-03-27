package com.ibm.genapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Policy entity representing insurance policies
 * Policy types: M=Motor, E=Endowment, H=House, C=Commercial
 */
@Entity
@Table(name = "policy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_number")
    private Long policyNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_number", nullable = false)
    @NotNull
    @JsonBackReference("customer-policies")
    private Customer customer;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "policy_type", length = 1, nullable = false)
    @NotNull
    private String policyType;

    @Column(name = "last_changed")
    private LocalDateTime lastChanged;

    @Column(name = "broker_id")
    private Integer brokerId;

    @Column(name = "brokers_reference", length = 10)
    private String brokersReference;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "commission")
    private Short commission;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("policy-endowment")
    private Endowment endowment;

    @OneToOne(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("policy-house")
    private House house;

    @OneToOne(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("policy-motor")
    private Motor motor;

    @OneToOne(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("policy-commercial")
    private Commercial commercial;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("policy-claims")
    private List<Claim> claims = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        lastChanged = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        lastChanged = LocalDateTime.now();
    }
}

// Made with Bob
