package com.ibm.genapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJsonSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void serializesCustomerPolicyGraphWithoutRecursiveBackReferences() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerNumber(1L);
        customer.setFirstName("Andrew");

        Policy policy = new Policy();
        policy.setPolicyNumber(10L);
        policy.setPolicyType("C");
        policy.setCustomer(customer);
        customer.getPolicies().add(policy);

        Commercial commercial = new Commercial();
        commercial.setPolicyNumber(10L);
        commercial.setCustomerName("Beebhouse");
        commercial.setPolicy(policy);
        policy.setCommercial(commercial);

        Claim claim = new Claim();
        claim.setClaimNumber(100L);
        claim.setCause("Fire");
        claim.setPolicy(policy);
        policy.getClaims().add(claim);

        String json = objectMapper.writeValueAsString(customer);

        assertThat(json).contains("\"customerNumber\":1");
        assertThat(json).contains("\"policyNumber\":10");
        assertThat(json).contains("\"commercial\":");
        assertThat(json).contains("\"claims\":");
        assertThat(json).doesNotContain("\"customer\":{");
        assertThat(json).doesNotContain("\"policy\":{\"policyNumber\":10");
    }
}
