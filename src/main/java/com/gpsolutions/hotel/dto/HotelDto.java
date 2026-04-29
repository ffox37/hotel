package com.gpsolutions.hotel.dto;

public record HotelDto(
    Long id,
    String name,
    String description,
    String brand,
    String address,
    String phone
) {

}
