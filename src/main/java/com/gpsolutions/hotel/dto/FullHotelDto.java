package com.gpsolutions.hotel.dto;

import java.util.List;

public record FullHotelDto(
    Long id,
    String name,
    String description,
    String brand,
    AddressDto addressDto,
    ContactsDto contactsDto,
    ArrivalTimeDto arrivalTimeDto,
    List<String> amenities
) {

}
