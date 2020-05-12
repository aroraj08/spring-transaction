package com.example.springjpa.service;

import com.example.springjpa.model.AddressDto;

public interface AddressService {

    void saveAddress(AddressDto address, Long customerId);
}
