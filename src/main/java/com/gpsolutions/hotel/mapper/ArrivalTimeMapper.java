package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.ArrivalTimeDto;
import com.gpsolutions.hotel.model.ArrivalTime;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface ArrivalTimeMapper {

  ArrivalTime fromDto(ArrivalTimeDto arrivalTimeDto);

  ArrivalTimeDto toDto(ArrivalTime arrivalTime);
}
