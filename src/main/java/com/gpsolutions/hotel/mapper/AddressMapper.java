package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.AddressDto;
import com.gpsolutions.hotel.model.Address;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface AddressMapper {

  Address fromDto(AddressDto addressDto);

  AddressDto toDto(Address address);
}
