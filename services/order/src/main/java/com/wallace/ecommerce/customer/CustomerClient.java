package com.wallace.ecommerce.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "costumer-service",
        url = "${application.config.customer-url}" //information from config-server -> configurations -> customer-service
)

public interface CustomerClient {
    /*
     * this is the same endpoint mapped on the CustomerController of the customer service
     */
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
