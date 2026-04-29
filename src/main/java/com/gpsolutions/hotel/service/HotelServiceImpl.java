package com.gpsolutions.hotel.service;

import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HistogramDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

  @Override
  public List<HotelDto> getAllHotels() {
    return List.of();
  }

  @Override
  public FullHotelDto getFullHotelInfo(final Long id) {
    return null;
  }

  @Override
  public List<HotelDto> searchForHotels(
      final String name,
      final String brand,
      final String city,
      final String country,
      final String amenities) {
    return List.of();
  }

  @Override
  public HotelDto createHotel(final CreateHotelDto createHotelDto) {
    return null;
  }

  @Override
  public void addAmenitiesToHotel(final Long id, final List<String> amenities) {

  }

  @Override
  public List<HistogramDto> getHotelHistogram(final Parameter parameter) {
    return List.of();
  }
}
