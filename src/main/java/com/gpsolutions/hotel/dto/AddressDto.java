package com.gpsolutions.hotel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressDto(
    @NotNull Long houseNumber,
    @NotNull @Size(max = 255) String street,
    @NotNull @Size(max = 50) String city,
    @NotNull @Size(max = 50) String country,
    @NotNull @Size(max = 10) String postCode
) {

}
