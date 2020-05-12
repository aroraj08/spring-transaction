package com.example.springjpa.mapper;

import com.example.springjpa.domain.Address;
import com.example.springjpa.model.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);
}
