package com.ibm.genapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Motor entity representing motor insurance policies
 */
@Entity
@Table(name = "motor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motor {

    @Id
    @Column(name = "policy_number")
    private Long policyNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "policy_number")
    private Policy policy;

    @Column(name = "make", length = 15)
    private String make;

    @Column(name = "model", length = 15)
    private String model;

    @Column(name = "value")
    private Integer value;

    @Column(name = "reg_number", length = 7)
    private String regNumber;

    @Column(name = "colour", length = 8)
    private String colour;

    @Column(name = "cc")
    private Short cc;

    @Column(name = "year_of_manufacture")
    private LocalDate yearOfManufacture;

    @Column(name = "premium")
    private Integer premium;

    @Column(name = "accidents")
    private Integer accidents;

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
