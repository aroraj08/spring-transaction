package com.example.springjpa.service;

import com.example.springjpa.domain.Address;
import com.example.springjpa.exceptions.CustomerNotFoundException;
import com.example.springjpa.mapper.AddressMapper;
import com.example.springjpa.mapper.CustomerMapper;
import com.example.springjpa.model.CustomerDto;
import com.example.springjpa.domain.Customer;
import com.example.springjpa.repository.AddressRepository;
import com.example.springjpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final AddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               AddressService addressService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.addressService = addressService;
    }

    @Override
    public Optional<List<CustomerDto>> getCustomers() {

        List<Customer> customerList = new ArrayList<>();
        this.customerRepository.findAll().forEach(c -> customerList.add(c));

        // convert Customer list to CustomerDto List using MapStruct
        List<CustomerDto>  customerDtoList = customerList.stream()
                    .map(c -> customerMapper.customerToCustomerDto(c))
                    .collect(Collectors.toList());

        return Optional.of(customerDtoList);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) throws CustomerNotFoundException {

        Customer customer = checkIfPresent(customerId);
        // map customer object to CustomerDto object using MapStruct
        CustomerDto customerDto = this.customerMapper.customerToCustomerDto(customer);
        return customerDto;
    }

    @Override
    public void updateCustomer(Long customerId, CustomerDto c) throws CustomerNotFoundException {

        Customer customer = checkIfPresent(customerId);
        // update Customer domain object with data from Dto and save it back to DB
        customer.setFirstName(c.getFirstName());
        customer.setLastName(c.getLastName());

        this.customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveCustomer(CustomerDto customerDto) {

        // saving customer data
        Customer customer = this.customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = this.customerRepository.save(customer);
        Long customerId = savedCustomer.getCustomerId();

        // saving address data
        this.addressService.saveAddress(customerDto.getAddressDto(), customerId);


        return customerId;
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {

        this.customerRepository.deleteByCustomerId(customerId);
    }

    public Customer checkIfPresent(Long customerId) throws CustomerNotFoundException {

        Optional<Customer> customerObj = customerRepository.findByCustomerId(customerId);
        Customer customer = customerObj.orElseThrow(() -> {
            return new CustomerNotFoundException("Customer not found : " + customerId);
        });

        return customer;
    }
}
