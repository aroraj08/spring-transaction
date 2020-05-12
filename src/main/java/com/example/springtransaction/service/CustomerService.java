package com.example.springtransaction.service;

import com.example.springtransaction.exceptions.CustomerNotFoundException;
import com.example.springtransaction.model.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<List<CustomerDto>> getCustomers();

    CustomerDto getCustomerById(Long customerId) throws CustomerNotFoundException;

    void updateCustomer(Long customerId, CustomerDto c) throws CustomerNotFoundException;

    Long saveCustomer(CustomerDto c);

    void deleteCustomer(Long customerId);
}
