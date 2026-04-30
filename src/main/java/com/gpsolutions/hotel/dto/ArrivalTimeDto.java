package com.gpsolutions.hotel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ArrivalTimeDto(
    @NotNull @Size(max = 5) String checkIn,
    @Size(max = 5) String checkOut
) {

}
