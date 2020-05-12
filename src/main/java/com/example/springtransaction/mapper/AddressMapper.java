package com.example.springtransaction.mapper;

import com.example.springtransaction.domain.Address;
import com.example.springtransaction.model.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);
}
