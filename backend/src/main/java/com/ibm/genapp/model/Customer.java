package com.ibm.genapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer entity representing insurance customers
 */
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_number")
    private Long customerNumber;

    @Column(name = "first_name", length = 10)
    @Size(max = 10)
    private String firstName;

    @Column(name = "last_name", length = 20)
    @Size(max = 20)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "house_name", length = 20)
    @Size(max = 20)
    private String houseName;

    @Column(name = "house_number", length = 4)
    @Size(max = 4)
    private String houseNumber;

    @Column(name = "postcode", length = 8)
    @Size(max = 8)
    private String postcode;

    @Column(name = "phone_home", length = 20)
    @Size(max = 20)
    private String phoneHome;

    @Column(name = "phone_mobile", length = 20)
    @Size(max = 20)
    private String phoneMobile;

    @Column(name = "email_address", length = 100)
    @Email
    @Size(max = 100)
    private String emailAddress;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("customer-policies")
    private List<Policy> policies = new ArrayList<>();

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
