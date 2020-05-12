package com.example.springjpa.mapper;

import com.example.springjpa.model.CustomerDto;
import com.example.springjpa.domain.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}
