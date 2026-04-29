package com.gpsolutions.hotel.controller;

import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HistogramDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Parameter;
import com.gpsolutions.hotel.service.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

  private final HotelService hotelService;

  @GetMapping("/hotels")
  private List<HotelDto> getAllHotels(){
    return hotelService.getAllHotels();
  }

  @GetMapping("/hotels/{id}")
  private FullHotelDto getFullHotelInfo(@PathVariable final Long id){
    return hotelService.getFullHotelInfo(id);
  }

  @GetMapping("/search")
  private List<HotelDto> searchForHotels(
      @RequestParam(name = "name") final String name,
      @RequestParam(name = "name") final String brand,
      @RequestParam(name = "brand") final String city,
      @RequestParam(name = "country") final String country,
      @RequestParam(name = "amenities") final String amenities){
    return hotelService.searchForHotels(name, brand, city, country, amenities);
  }

  @PostMapping("/hotels")
  private HotelDto createHotel(@RequestBody final CreateHotelDto createHotelDto){
    return hotelService.createHotel(createHotelDto);
  }

  @PostMapping("/hotels/{id}/amenities")
  private void addAmenitiesToHotel(
      @PathVariable final Long id,
      @RequestBody final List<String> amenities){
    hotelService.addAmenitiesToHotel(id, amenities);
  }

  @GetMapping("/histogram/{param}")
  private List<HistogramDto> getHotelHistogram(@PathVariable final Parameter param){
    return hotelService.getHotelHistogram(param);
  }
}
