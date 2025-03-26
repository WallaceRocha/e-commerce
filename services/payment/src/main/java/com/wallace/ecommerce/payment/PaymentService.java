package com.wallace.ecommerce.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public Long createPayment(@Valid PaymentRequest request) {
        return null;
    }
}
