package com.example.springtransaction.mapper;

import com.example.springtransaction.model.CustomerDto;
import com.example.springtransaction.domain.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}
