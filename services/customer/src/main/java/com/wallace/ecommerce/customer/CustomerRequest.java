package com.wallace.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        @NotBlank()
        String firstname,
        @NotNull(message = "Customer lastname is required")
        @NotBlank()
        String lastname,
        @NotNull(message = "Customer email is required")
        @NotBlank()
        @Email(message = "Customer email is invalid")
        String email,
        Address address) {
}
