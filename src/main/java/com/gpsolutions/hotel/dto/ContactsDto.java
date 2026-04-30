package com.gpsolutions.hotel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContactsDto(
    @NotNull @Size(max = 64) String phone,
    @NotNull @Size(max = 320) String email
) {

}
