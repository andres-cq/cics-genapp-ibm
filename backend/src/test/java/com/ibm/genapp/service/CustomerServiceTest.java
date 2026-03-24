package com.ibm.genapp.service;

import com.ibm.genapp.exception.ResourceNotFoundException;
import com.ibm.genapp.model.Customer;
import com.ibm.genapp.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setCustomerNumber(1L);
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setDateOfBirth(LocalDate.of(1980, 1, 1));
        testCustomer.setPostcode("12345");
        testCustomer.setEmailAddress("john.doe@example.com");
    }

    @Test
    void getAllCustomers_ShouldReturnAllCustomers() {
        // Arrange
        List<Customer> customers = Arrays.asList(testCustomer);
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.getAllCustomers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testCustomer.getFirstName(), result.get(0).getFirstName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void getCustomerById_WhenExists_ShouldReturnCustomer() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        // Act
        Customer result = customerService.getCustomerById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(testCustomer.getCustomerNumber(), result.getCustomerNumber());
        assertEquals(testCustomer.getFirstName(), result.getFirstName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void getCustomerById_WhenNotExists_ShouldThrowException() {
        // Arrange
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(999L);
        });
        verify(customerRepository, times(1)).findById(999L);
    }

    @Test
    void createCustomer_ShouldSaveAndReturnCustomer() {
        // Arrange
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // Act
        Customer result = customerService.createCustomer(testCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(testCustomer.getFirstName(), result.getFirstName());
        verify(customerRepository, times(1)).save(testCustomer);
    }

    @Test
    void updateCustomer_WhenExists_ShouldUpdateAndReturn() {
        // Arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Smith");
        
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // Act
        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        // Assert
        assertNotNull(result);
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_WhenExists_ShouldDelete() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        doNothing().when(customerRepository).delete(testCustomer);

        // Act
        customerService.deleteCustomer(1L);

        // Assert
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).delete(testCustomer);
    }

    @Test
    void searchCustomersByLastName_ShouldReturnMatchingCustomers() {
        // Arrange
        List<Customer> customers = Arrays.asList(testCustomer);
        when(customerRepository.findByLastNameContainingIgnoreCase("Doe")).thenReturn(customers);

        // Act
        List<Customer> result = customerService.searchCustomersByLastName("Doe");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Doe", result.get(0).getLastName());
        verify(customerRepository, times(1)).findByLastNameContainingIgnoreCase("Doe");
    }
}

// Made with Bob
