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
class AmenityRepositoryTest {

  @Autowired
  private AmenityRepository amenityRepository;
  @Autowired
  private HotelRepository hotelRepository;
  private Amenity amenity;

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
    final Hotel hotel = hotelRepository.save(new Hotel(
        null,
        "DoubleTree by Hilton Minsk",
        "The DoubleTree by Hilton Hotel Minsk offers ...",
        "Hilton",
        address,
        contacts,
        arrivalTime,
        null));
    amenity = amenityRepository.save(new Amenity(
        null,
        "Free Parking",
        List.of(hotel)));
  }

  @Test
  void existsByNameTest_shouldReturnTrue(){
    Assertions.assertTrue(amenityRepository.existsByName(amenity.getName()));
  }

  @Test
  void existsByNameTest_shouldReturnFalse(){
    Assertions.assertFalse(amenityRepository.existsByName("Wrong Name"));
  }

  @Test
  void shouldReturnAmenityByName(){
    final Amenity testAmenity = amenityRepository.findByName(amenity.getName());
    Assertions.assertEquals(amenity.getId(), testAmenity.getId());
    Assertions.assertEquals(amenity.getName(), testAmenity.getName());
    Assertions.assertEquals(amenity.getHotels().getFirst(), testAmenity.getHotels().getFirst());
  }

  @Test
  void shouldReturnAmenitiesByName(){
    final Amenity testAmenity = amenityRepository.findAllByNameIn(List.of(amenity.getName())).getFirst();
    Assertions.assertEquals(amenity.getId(), testAmenity.getId());
    Assertions.assertEquals(amenity.getName(), testAmenity.getName());
    Assertions.assertEquals(amenity.getHotels().getFirst(), testAmenity.getHotels().getFirst());
  }
}
