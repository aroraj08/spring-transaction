package com.example.springtransaction.repository;

import com.example.springtransaction.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
