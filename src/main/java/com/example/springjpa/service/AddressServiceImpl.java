package com.example.springjpa.service;

import com.example.springjpa.domain.Address;
import com.example.springjpa.mapper.AddressMapper;
import com.example.springjpa.model.AddressDto;
import com.example.springjpa.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAddress(AddressDto addressDto, Long customerId) {

        Address address = this.addressMapper.addressDtoToAddress(addressDto);
        address.setCustomerId(customerId);

        this.addressRepository.save(address);
        //this.addressRepository.save(null);
    }
}
