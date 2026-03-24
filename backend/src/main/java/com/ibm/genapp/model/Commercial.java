package com.ibm.genapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Commercial entity representing commercial property insurance policies
 */
@Entity
@Table(name = "commercial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commercial {

    @Id
    @Column(name = "policy_number")
    private Long policyNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "policy_number")
    private Policy policy;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "renewal_date")
    private LocalDate renewalDate;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "zipcode", length = 8)
    private String zipcode;

    @Column(name = "latitude_n", length = 11)
    private String latitudeN;

    @Column(name = "longitude_w", length = 11)
    private String longitudeW;

    @Column(name = "customer_name", length = 255)
    private String customerName;

    @Column(name = "property_type", length = 255)
    private String propertyType;

    @Column(name = "fire_peril")
    private Short firePeril;

    @Column(name = "fire_premium")
    private Integer firePremium;

    @Column(name = "crime_peril")
    private Short crimePeril;

    @Column(name = "crime_premium")
    private Integer crimePremium;

    @Column(name = "flood_peril")
    private Short floodPeril;

    @Column(name = "flood_premium")
    private Integer floodPremium;

    @Column(name = "weather_peril")
    private Short weatherPeril;

    @Column(name = "weather_premium")
    private Integer weatherPremium;

    @Column(name = "status")
    private Short status;

    @Column(name = "rejection_reason", length = 255)
    private String rejectionReason;

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
