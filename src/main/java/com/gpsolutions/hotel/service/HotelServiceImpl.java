package com.gpsolutions.hotel.service;

import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HistogramDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.exceptions.NotFoundException;
import com.gpsolutions.hotel.mapper.HotelMapper;
import com.gpsolutions.hotel.model.Amenity;
import com.gpsolutions.hotel.model.Hotel;
import com.gpsolutions.hotel.model.Parameter;
import com.gpsolutions.hotel.repository.AmenityRepository;
import com.gpsolutions.hotel.repository.HotelRepository;
import com.gpsolutions.hotel.repository.HotelSpecification;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

  private final HotelRepository hotelRepository;
  private final HotelMapper hotelMapper;
  private final AmenityRepository amenityRepository;

  @Override
  public List<HotelDto> getAllHotels() {
    final List<Hotel> hotels = hotelRepository.findAll();
    return hotels.stream().map(hotelMapper::toDto).toList();
  }

  @Override
  public FullHotelDto getFullHotelInfo(final Long id) {
    final Hotel hotel = hotelRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Hotel with id=" + id + " wasn't found.")
    );
    return hotelMapper.toFullDto(hotel);
  }

  @Override
  public List<HotelDto> searchForHotels(
      final String name,
      final String brand,
      final String city,
      final String country,
      final List<String> amenitiesNames) {
    final List<Amenity> amenities = amenityRepository.findAllByNameIn(amenitiesNames);
    final Specification<Hotel> spec = HotelSpecification.withFilters(
        name,
        brand,
        city,
        country,
        amenities);
    final List<Hotel> hotels = hotelRepository.findAll(spec);
    return hotels.stream().map(hotelMapper::toDto).toList();
  }

  @Override
  public HotelDto createHotel(final CreateHotelDto createHotelDto) {
    Hotel hotel = hotelMapper.fromDto(createHotelDto);
    hotel = hotelRepository.save(hotel);
    return hotelMapper.toDto(hotel);
  }

  @Override
  public void addAmenitiesToHotel(final Long id, final List<String> amenitiesNames) {
    Hotel hotel = hotelRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Hotel with id=" + id + " wasn't found."));
    List<Amenity> amenities = (hotel.getAmenities() == null) ?
        new ArrayList<>() : hotel.getAmenities();
    for(String amenityName : amenitiesNames){
      if(amenityRepository.existsByName(amenityName)){
        final Amenity existingAmenity = amenityRepository.findByName(amenityName);
        if(hotel.getAmenities() != null && !hotel.getAmenities().contains(existingAmenity)){
          amenities.add(existingAmenity);
        }
      }
      else {
        amenities.add(amenityRepository.save(
            Amenity.builder()
                .name(amenityName)
                .build()));
      }
    }
    hotel.setAmenities(amenities);
    hotelRepository.save(hotel);
  }

  @Override
  public List<HistogramDto> getHotelHistogram(final Parameter parameter) {
    return List.of();
  }
}
