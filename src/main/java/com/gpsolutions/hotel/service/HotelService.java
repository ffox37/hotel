package com.gpsolutions.hotel.service;

import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HistogramDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Parameter;
import java.util.List;

public interface HotelService {

  List<HotelDto> getAllHotels();

  FullHotelDto getFullHotelInfo(Long id);

  List<HotelDto> searchForHotels(
      String name,
      String brand,
      String city,
      String countries,
      List<String> amenitiesNames);

  HotelDto createHotel(CreateHotelDto createHotelDto);

  void addAmenitiesToHotel(Long id, List<String> amenitiesNames);

  List<HistogramDto> getHotelHistogram(Parameter parameter);
}
