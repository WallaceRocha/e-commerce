package com.wallace.ecommerce.customer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

import java.util.List;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String create(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public CustomerResponse findById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(
                () -> new CustomerNotFoundException(format("Customer with id %s not found", id)));
    }

    public Boolean existsById(String id) {
        return repository.findById(id).isPresent();
    }

    public void update(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(format("Unable to update customer with id %s", request.id())));

        mergeCustomer(customer, request);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotEmpty(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotEmpty(request.lastname())) {
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotEmpty(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
        repository.save(customer);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<CustomerResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
}
