package com.wallace.ecommerce.customer;

/*
    * Here of course the service should have its own response object instead of reusing the same from the customer service
 */
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
