package com.gpsolutions.hotel.dto;

public record CreateHotelDto(
    String name,
    String description,
    String brand,
    AddressDto address,
    ContactsDto contacts,
    ArrivalTimeDto arrivalTime
) {

}
