package com.example.springtransaction.service;

import com.example.springtransaction.model.AddressDto;

public interface AddressService {

    void saveAddress(AddressDto address, Long customerId);
}
