package com.ibm.genapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Endowment entity representing endowment insurance policies
 */
@Entity
@Table(name = "endowment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endowment {

    @Id
    @Column(name = "policy_number")
    private Long policyNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "policy_number")
    private Policy policy;

    @Column(name = "equities", length = 1)
    private String equities;

    @Column(name = "with_profits", length = 1)
    private String withProfits;

    @Column(name = "managed_fund", length = 1)
    private String managedFund;

    @Column(name = "fund_name", length = 10)
    private String fundName;

    @Column(name = "term")
    private Short term;

    @Column(name = "sum_assured")
    private Integer sumAssured;

    @Column(name = "life_assured", length = 31)
    private String lifeAssured;

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
