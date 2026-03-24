package com.ibm.genapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * House entity representing house insurance policies
 */
@Entity
@Table(name = "house")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {

    @Id
    @Column(name = "policy_number")
    private Long policyNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "policy_number")
    private Policy policy;

    @Column(name = "property_type", length = 15)
    private String propertyType;

    @Column(name = "bedrooms")
    private Short bedrooms;

    @Column(name = "value")
    private Integer value;

    @Column(name = "house_name", length = 20)
    private String houseName;

    @Column(name = "house_number", length = 4)
    private String houseNumber;

    @Column(name = "postcode", length = 8)
    private String postcode;

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
