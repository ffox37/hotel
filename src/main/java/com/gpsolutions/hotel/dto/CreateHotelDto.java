package com.gpsolutions.hotel.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateHotelDto(
    @NotNull @Size(max = 255) String name,
    @Size(max = 255) String description,
    @NotNull @Size(max = 255) String brand,
    @Valid AddressDto address,
    @Valid ContactsDto contacts,
    @Valid ArrivalTimeDto arrivalTime
) {

}
