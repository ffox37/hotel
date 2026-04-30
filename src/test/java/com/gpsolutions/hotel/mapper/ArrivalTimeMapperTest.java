package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.ArrivalTimeDto;
import com.gpsolutions.hotel.model.ArrivalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrivalTimeMapperTest {

  private final ArrivalTimeMapper arrivalTimeMapper = new ArrivalTimeMapperImpl();

  @Test
  void shouldMapArrivalDtoToArrivalEntity(){
    final ArrivalTimeDto arrivalTimeDto = new ArrivalTimeDto(
        "14:00",
        "12:00");
    final ArrivalTime arrivalTime = arrivalTimeMapper.fromDto(arrivalTimeDto);
    Assertions.assertEquals(arrivalTimeDto.checkIn(), arrivalTime.getCheckIn());
    Assertions.assertEquals(arrivalTimeDto.checkOut(), arrivalTime.getCheckOut());
  }

  @Test
  void shouldMapArrivalEntityToArrivalDto(){
    final ArrivalTime arrivalTime = new ArrivalTime(
        "13:00",
        "14:00");
    final ArrivalTimeDto arrivalTimeDto = arrivalTimeMapper.toDto(arrivalTime);
    Assertions.assertEquals(arrivalTime.getCheckIn(), arrivalTimeDto.checkIn());
    Assertions.assertEquals(arrivalTime.getCheckOut(), arrivalTimeDto.checkOut());
  }
}
