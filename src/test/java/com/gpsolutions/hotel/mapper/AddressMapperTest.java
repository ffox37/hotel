package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.AddressDto;
import com.gpsolutions.hotel.model.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AddressMapperTest {

  private final AddressMapper addressMapper = new AddressMapperImpl();

  @Test
  void shouldMapAddressDtoToAddressEntity(){
    final AddressDto addressDto = new AddressDto(
        1L,
        "Karl Marks street",
        "Minsk",
        "Belarus",
        "220000");
    final Address address = addressMapper.fromDto(addressDto);
    Assertions.assertEquals(addressDto.houseNumber(), address.getHouseNumber());
    Assertions.assertEquals(addressDto.street(), address.getStreet());
    Assertions.assertEquals(addressDto.city(), address.getCity());
    Assertions.assertEquals(addressDto.country(), address.getCountry());
    Assertions.assertEquals(addressDto.postCode(), address.getPostCode());
  }

  @Test
  void shouldMapAddressEntityToAddressDto(){
    final Address address = new Address(
        9L,
        "Independence Avenue",
        "Brest",
        "Belarus",
        "230000");
    final AddressDto addressDto = addressMapper.toDto(address);
    Assertions.assertEquals(address.getHouseNumber(), addressDto.houseNumber());
    Assertions.assertEquals(address.getStreet(), addressDto.street());
    Assertions.assertEquals(address.getCity(), addressDto.city());
    Assertions.assertEquals(address.getCountry(), addressDto.country());
    Assertions.assertEquals(address.getPostCode(), addressDto.postCode());
  }
}
