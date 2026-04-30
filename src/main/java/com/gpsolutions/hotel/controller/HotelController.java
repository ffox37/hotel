package com.gpsolutions.hotel.controller;

import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Parameter;
import com.gpsolutions.hotel.service.HotelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class HotelController {

  private final HotelService hotelService;

  @GetMapping("/hotels")
  public List<HotelDto> getAllHotels(){
    return hotelService.getAllHotels();
  }

  @GetMapping("/hotels/{id}")
  public FullHotelDto getFullHotelInfo(@PathVariable final Long id){
    return hotelService.getFullHotelInfo(id);
  }

  @GetMapping("/search")
  public List<HotelDto> searchForHotels(
      @Size(max = 255) @RequestParam(name = "name", required = false) final String name,
      @Size(max = 255) @RequestParam(name = "brand", required = false) final String brand,
      @Size(max = 50) @RequestParam(name = "city", required = false) final String city,
      @Size(max = 50) @RequestParam(name = "country", required = false) final String country,
      @RequestParam(name = "amenities", required = false) final List<@Size(max = 255) String> amenities){
    return hotelService.searchForHotels(name, brand, city, country, amenities);
  }

  @PostMapping("/hotels")
  public HotelDto createHotel(@Valid @RequestBody final CreateHotelDto createHotelDto){
    return hotelService.createHotel(createHotelDto);
  }

  @PostMapping("/hotels/{id}/amenities")
  public void addAmenitiesToHotel(
      @PathVariable final Long id,
      @RequestBody final List<@Size(max = 255) String> amenitiesNames){
    hotelService.addAmenitiesToHotel(id, amenitiesNames);
  }

  @GetMapping("/histogram/{param}")
  public Map<String, Long> getHotelHistogram(@PathVariable final String param){
    return hotelService.getHotelHistogram(Parameter.getParameter(param));
  }
}
