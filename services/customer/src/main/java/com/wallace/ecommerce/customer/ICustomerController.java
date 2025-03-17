package com.wallace.ecommerce.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Tag(name="Customer", description="Operations related to customers")
public interface ICustomerController {

    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Once the customer is created successfully")
    })
    @PostMapping
    ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request);

    @Operation(summary = "Update an existing customer", description = "Search for a customer by the given id and updates it with the new information")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "202", description = "Once the customer is updated successfully"),
            @ApiResponse(responseCode = "404", description = "If the customer is not found")
    })
    @PutMapping
    ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request);


    @Operation(summary = "Find all customers", description="Returns a list of all customers excluding deleted ones")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "The list of fetched customers")
    })
    @GetMapping
    ResponseEntity<List<CustomerResponse>> findAll();

    @Operation(summary = "Check if the customer exists", description="Returns true if the customer exists, false otherwise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "True or false in case of the customer was found or not")
    })
    @GetMapping("/exists/{customer-id}")
    ResponseEntity<Boolean> existsById(@PathVariable("customer-id")  String customerId);

    @Operation(summary = "Find a customer by id", description="Returns the customer with the given id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The customer object found"),
            @ApiResponse(responseCode = "404", description = "Customer not found with the giver id")
    })
    @GetMapping("/{customer-id}")
    ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId);

    @Operation(summary = "Delete customer", description = "Deletes the customer with the giver id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Once the customer is deleted successfully")
    })
    @DeleteMapping("/{customer-id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId);
}
