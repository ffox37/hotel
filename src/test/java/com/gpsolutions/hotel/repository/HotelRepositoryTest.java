package com.gpsolutions.hotel.repository;

import com.gpsolutions.hotel.model.Address;
import com.gpsolutions.hotel.model.Amenity;
import com.gpsolutions.hotel.model.ArrivalTime;
import com.gpsolutions.hotel.model.Contacts;
import com.gpsolutions.hotel.model.Hotel;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
class HotelRepositoryTest {

  @Autowired
  private HotelRepository hotelRepository;
  @Autowired
  private AmenityRepository amenityRepository;
  private Hotel hotel;

  @BeforeEach
  void setUp(){
    final Address address = new Address(
        1L,
        "Karl Libnecht street",
        "Grodno",
        "Belarus",
        "225000");
    final Contacts contacts = new Contacts(
        "+327445555555",
        "testemail@gmail.com");
    final ArrivalTime arrivalTime = new ArrivalTime(
        "14:00",
        "12:00");
    final List<Amenity> amenities = List.of(
        amenityRepository.save(new Amenity(null, "Free Wi-Fi", null)));
    hotel = new Hotel(
        null,
        "DoubleTree by Hilton Minsk",
        "The DoubleTree by Hilton Hotel Minsk offers ...",
        "Hilton",
        address,
        contacts,
        arrivalTime,
        amenities);
    hotelRepository.save(hotel);
  }

  @Test
  void shouldReturnBrandHistogramObject(){
    final List<Object[]> histogramObjects = hotelRepository.countAndGetNumberOfHotelsForAllBrands();
    Assertions.assertEquals(1, histogramObjects.size());
    Assertions.assertEquals(2, histogramObjects.getFirst().length);
    Assertions.assertEquals(hotel.getBrand(), histogramObjects.getFirst()[0]);
    Assertions.assertEquals(1L, histogramObjects.getFirst()[1]);
  }

  @Test
  void shouldReturnCityHistogramObject(){
    final List<Object[]> histogramObjects = hotelRepository.countAndGetNumberOfHotelsForAllCities();
    Assertions.assertEquals(1, histogramObjects.size());
    Assertions.assertEquals(2, histogramObjects.getFirst().length);
    Assertions.assertEquals(hotel.getAddress().getCity(), histogramObjects.getFirst()[0]);
    Assertions.assertEquals(1L, histogramObjects.getFirst()[1]);
  }

  @Test
  void shouldReturnCountryHistogramObject(){
    final List<Object[]> histogramObjects = hotelRepository.countAndGetNumberOfHotelsForAllCountries();
    Assertions.assertEquals(1, histogramObjects.size());
    Assertions.assertEquals(2, histogramObjects.getFirst().length);
    Assertions.assertEquals(hotel.getAddress().getCountry(), histogramObjects.getFirst()[0]);
    Assertions.assertEquals(1L, histogramObjects.getFirst()[1]);
  }

  @Test
  void shouldReturnAmenityHistogramObject(){
    final List<Object[]> histogramObjects = hotelRepository.countAndGetNumberOfHotelsForAllAmenities();
    Assertions.assertEquals(1, histogramObjects.size());
    Assertions.assertEquals(2, histogramObjects.getFirst().length);
    Assertions.assertEquals(hotel.getAmenities().getFirst().getName(), histogramObjects.getFirst()[0]);
    Assertions.assertEquals(1L, histogramObjects.getFirst()[1]);
  }
}
