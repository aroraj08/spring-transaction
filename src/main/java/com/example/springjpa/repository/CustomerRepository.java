package com.example.springjpa.repository;

import com.example.springjpa.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Optional<Customer> findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
}
