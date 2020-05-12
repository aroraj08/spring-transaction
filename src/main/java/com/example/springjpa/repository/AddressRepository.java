package com.example.springjpa.repository;

import com.example.springjpa.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
