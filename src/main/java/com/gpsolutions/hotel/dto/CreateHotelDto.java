package com.gpsolutions.hotel.dto;

public record CreateHotelDto(
    String name,
    String description,
    String brand,
    AddressDto addressDto,
    ContactsDto contactsDto,
    ArrivalTimeDto arrivalTimeDto
) {

}
