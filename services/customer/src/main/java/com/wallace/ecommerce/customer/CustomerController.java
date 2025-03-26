package com.wallace.ecommerce.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController implements ICustomerController {

    private final CustomerService service;

    @Override
    public ResponseEntity<String> createCustomer(CustomerRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @Override
    public ResponseEntity<Void> updateCustomer(CustomerRequest request) {
        service.update(request);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<Boolean> existsById(String customerId) {
        return ResponseEntity.ok(service.existsById(customerId));
    }

    @Override
    public ResponseEntity<CustomerResponse> findById(String customerId) {
        return ResponseEntity.ok(service.findById(customerId));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String customerId) {
        service.delete(customerId);
        return ResponseEntity.accepted().build();
    }

}
