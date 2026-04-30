package com.gpsolutions.hotel.service;

import com.gpsolutions.hotel.dto.AddressDto;
import com.gpsolutions.hotel.dto.ArrivalTimeDto;
import com.gpsolutions.hotel.dto.ContactsDto;
import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Address;
import com.gpsolutions.hotel.model.Amenity;
import com.gpsolutions.hotel.model.ArrivalTime;
import com.gpsolutions.hotel.model.Contacts;
import com.gpsolutions.hotel.model.Hotel;
import com.gpsolutions.hotel.repository.HotelRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class HotelServiceTest {

  @Autowired
  private HotelServiceImpl hotelService;
  @Autowired
  private HotelRepository hotelRepository;

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
        new Amenity(1L, "Free Wi-Fi", null));
    hotel = hotelRepository.save(new Hotel(
        null,
        "DoubleTree by Hilton Minsk",
        "The DoubleTree by Hilton Hotel Minsk offers ...",
        "Hilton",
        address,
        contacts,
        arrivalTime,
        List.of()));
  }

  @Test
  void shouldGetAllHotels(){
    final List<HotelDto> hotelDtos = hotelService.getAllHotels();
    Assertions.assertEquals(1, hotelDtos.size());
    Assertions.assertEquals(hotel.getId(), hotelDtos.getFirst().id());
    Assertions.assertEquals(hotel.getName(), hotelDtos.getFirst().name());
    Assertions.assertEquals(hotel.getDescription(), hotelDtos.getFirst().description());
    Assertions.assertEquals(hotel.getBrand(), hotelDtos.getFirst().brand());
    Assertions.assertEquals(hotel.getAddress().toString(), hotelDtos.getFirst().address());
    Assertions.assertEquals(hotel.getContacts().getPhone(), hotelDtos.getFirst().phone());
  }

  @Test
  void shouldGetFullHotelInfo(){
    final FullHotelDto fullHotelDto = hotelService.getFullHotelInfo(hotel.getId());
    Assertions.assertEquals(hotel.getId(), fullHotelDto.id());
    Assertions.assertEquals(hotel.getName(), fullHotelDto.name());
    Assertions.assertEquals(hotel.getDescription(), fullHotelDto.description());
    Assertions.assertEquals(hotel.getBrand(), fullHotelDto.brand());
    Assertions.assertEquals(hotel.getAddress().getHouseNumber(), fullHotelDto.addressDto().houseNumber());
    Assertions.assertEquals(hotel.getAddress().getStreet(), fullHotelDto.addressDto().street());
    Assertions.assertEquals(hotel.getAddress().getCity(), fullHotelDto.addressDto().city());
    Assertions.assertEquals(hotel.getAddress().getCountry(), fullHotelDto.addressDto().country());
    Assertions.assertEquals(hotel.getAddress().getPostCode(), fullHotelDto.addressDto().postCode());
    Assertions.assertEquals(hotel.getContacts().getPhone(), fullHotelDto.contactsDto().phone());
    Assertions.assertEquals(hotel.getContacts().getEmail(), fullHotelDto.contactsDto().email());
    Assertions.assertEquals(hotel.getArrivalTime().getCheckIn(), fullHotelDto.arrivalTimeDto().checkIn());
    Assertions.assertEquals(hotel.getArrivalTime().getCheckOut(), fullHotelDto.arrivalTimeDto().checkOut());
    Assertions.assertTrue(fullHotelDto.amenities().isEmpty());
  }

  @Test
  void shouldSearchForHotels(){
    final List<HotelDto> hotelDtos = hotelService.searchForHotels(
        hotel.getName(),
        hotel.getBrand(),
        hotel.getAddress().getCity(),
        hotel.getAddress().getCountry(),
        null);
    Assertions.assertEquals(1, hotelDtos.size());
    Assertions.assertEquals(hotel.getId(), hotelDtos.getFirst().id());
    Assertions.assertEquals(hotel.getName(), hotelDtos.getFirst().name());
    Assertions.assertEquals(hotel.getDescription(), hotelDtos.getFirst().description());
    Assertions.assertEquals(hotel.getBrand(), hotelDtos.getFirst().brand());
    Assertions.assertEquals(hotel.getAddress().toString(), hotelDtos.getFirst().address());
    Assertions.assertEquals(hotel.getContacts().getPhone(), hotelDtos.getFirst().phone());
  }

  @Test
  void shouldCreateHotel(){
    final AddressDto addressDto = new AddressDto(
        1L,
        "Gogolya avenue",
        "Brest",
        "Belarus",
        "240000");
    final ContactsDto contactsDto = new ContactsDto(
        "+327441111111",
        "testemail@gmail.com");
    final ArrivalTimeDto arrivalTimeDto = new ArrivalTimeDto(
        "13:00",
        "13:00");
    final CreateHotelDto createHotelDto = new CreateHotelDto(
        "Hampton by Hilton Brest",
        "A modern hotel located at the ...",
        "Hilton",
        addressDto,
        contactsDto,
        arrivalTimeDto);
    final HotelDto hotelDto = hotelService.createHotel(createHotelDto);

    Assertions.assertEquals(createHotelDto.name(), hotelDto.name());
    Assertions.assertEquals(createHotelDto.description(), hotelDto.description());
    Assertions.assertEquals(createHotelDto.brand(), hotelDto.brand());
    Assertions.assertEquals("1 Gogolya avenue Brest Belarus 240000", hotelDto.address());
    Assertions.assertEquals(createHotelDto.contacts().phone(), hotelDto.phone());
  }
}
