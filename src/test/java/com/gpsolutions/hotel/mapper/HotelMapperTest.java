package com.gpsolutions.hotel.mapper;

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
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelMapperTest {

  private final AddressMapper addressMapper = new AddressMapperImpl();
  private final ContactsMapper contactsMapper = new ContactsMapperImpl();
  private final ArrivalTimeMapper arrivalTimeMapper = new ArrivalTimeMapperImpl();
  private final HotelMapper hotelMapper = new HotelMapperImpl(
      addressMapper,
      contactsMapper,
      arrivalTimeMapper);
  private Hotel hotel;
  private CreateHotelDto createHotelDto;

  @BeforeEach
  void setUp(){
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
    createHotelDto = new CreateHotelDto(
        "Hampton by Hilton Brest",
        "A modern hotel located at the ...",
        "Hilton",
        addressDto,
        contactsDto,
        arrivalTimeDto);

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
    hotel = new Hotel(
        1L,
        "DoubleTree by Hilton Minsk",
        "The DoubleTree by Hilton Hotel Minsk offers ...",
        "Hilton",
        address,
        contacts,
        arrivalTime,
        amenities);
  }

  @Test
  void shouldMapCreateHotelDtoToHotelEntity(){
    final Hotel hotel = hotelMapper.fromDto(createHotelDto);
    Assertions.assertNull(hotel.getId());
    Assertions.assertEquals(createHotelDto.name(), hotel.getName());
    Assertions.assertEquals(createHotelDto.description(), hotel.getDescription());
    Assertions.assertEquals(createHotelDto.brand(), hotel.getBrand());
    Assertions.assertEquals(
        createHotelDto.address().houseNumber(),
        hotel.getAddress().getHouseNumber());
    Assertions.assertEquals(
        createHotelDto.address().street(),
        hotel.getAddress().getStreet());
    Assertions.assertEquals(
        createHotelDto.address().city(),
        hotel.getAddress().getCity());
    Assertions.assertEquals(
        createHotelDto.address().country(),
        hotel.getAddress().getCountry());
    Assertions.assertEquals(
        createHotelDto.address().postCode(),
        hotel.getAddress().getPostCode());
    Assertions.assertEquals(
        createHotelDto.contacts().phone(),
        hotel.getContacts().getPhone());
    Assertions.assertEquals(
        createHotelDto.contacts().email(),
        hotel.getContacts().getEmail());
    Assertions.assertEquals(
        createHotelDto.arrivalTime().checkIn(),
        hotel.getArrivalTime().getCheckIn());
    Assertions.assertEquals(
        createHotelDto.arrivalTime().checkOut(),
        hotel.getArrivalTime().getCheckOut());
    Assertions.assertNull(hotel.getAmenities());
  }

  @Test
  void shouldMapHotelEntityToHotelDto(){
    final HotelDto hotelDto = hotelMapper.toDto(hotel);
    Assertions.assertEquals(this.hotel.getId(), hotelDto.id());
    Assertions.assertEquals(this.hotel.getName(), hotelDto.name());
    Assertions.assertEquals(this.hotel.getDescription(), hotelDto.description());
    Assertions.assertEquals(this.hotel.getBrand(), hotelDto.brand());
    Assertions.assertEquals(this.hotel.getContacts().getPhone(), hotelDto.phone());
    Assertions.assertEquals(this.hotel.getAddress().toString(), hotelDto.address());
  }

  @Test
  void shouldMapHotelEntityToFullHotelDto(){
    final FullHotelDto fullHotelDto = hotelMapper.toFullDto(hotel);
    Assertions.assertEquals(this.hotel.getId(), fullHotelDto.id());
    Assertions.assertEquals(this.hotel.getName(), fullHotelDto.name());
    Assertions.assertEquals(this.hotel.getDescription(), fullHotelDto.description());
    Assertions.assertEquals(this.hotel.getBrand(), fullHotelDto.brand());
    Assertions.assertEquals(
        this.hotel.getAddress().getHouseNumber(),
        fullHotelDto.address().houseNumber());
    Assertions.assertEquals(
        this.hotel.getAddress().getStreet(),
        fullHotelDto.address().street());
    Assertions.assertEquals(
        this.hotel.getAddress().getCity(),
        fullHotelDto.address().city());
    Assertions.assertEquals(
        this.hotel.getAddress().getCountry(),
        fullHotelDto.address().country());
    Assertions.assertEquals(
        this.hotel.getAddress().getPostCode(),
        fullHotelDto.address().postCode());
    Assertions.assertEquals(
        this.hotel.getContacts().getPhone(),
        fullHotelDto.contacts().phone());
    Assertions.assertEquals(
        this.hotel.getContacts().getEmail(),
        fullHotelDto.contacts().email());
    Assertions.assertEquals(
        this.hotel.getArrivalTime().getCheckIn(),
        fullHotelDto.arrivalTime().checkIn());
    Assertions.assertEquals(
        this.hotel.getArrivalTime().getCheckOut(),
        fullHotelDto.arrivalTime().checkOut());
    Assertions.assertEquals(
        this.hotel.getAmenities().getFirst().getName(),
        fullHotelDto.amenities().getFirst());
  }
}
