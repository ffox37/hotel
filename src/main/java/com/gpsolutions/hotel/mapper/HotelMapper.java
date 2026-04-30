package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class,
uses = {AddressMapper.class, ContactsMapper.class, ArrivalTimeMapper.class})
public interface HotelMapper {

  @Mapping(target = "amenities", ignore = true)
  Hotel fromDto(CreateHotelDto createHotelDto);

  @Mapping(target = "address", expression = "java(hotel.getAddress().toString())")
  @Mapping(target = "phone", expression = "java(hotel.getContacts().getPhone())")
  HotelDto toDto(Hotel hotel);

  @Mapping(target = "amenities", expression = "java(hotel.getAmenities().stream().map(a -> a.getName()).toList())")
  @Mapping(target = "addressDto", source = "address")
  @Mapping(target = "contactsDto", source = "contacts")
  @Mapping(target = "arrivalTimeDto", source = "arrivalTime")
  FullHotelDto toFullDto(Hotel hotel);
}
