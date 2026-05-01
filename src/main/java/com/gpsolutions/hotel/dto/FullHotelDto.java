package com.gpsolutions.hotel.dto;

import java.util.List;

public record FullHotelDto(
    Long id,
    String name,
    String description,
    String brand,
    AddressDto address,
    ContactsDto contacts,
    ArrivalTimeDto arrivalTime,
    List<String> amenities
) {

}
