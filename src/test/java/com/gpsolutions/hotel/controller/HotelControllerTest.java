package com.gpsolutions.hotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.hotel.dto.AddressDto;
import com.gpsolutions.hotel.dto.ArrivalTimeDto;
import com.gpsolutions.hotel.dto.ContactsDto;
import com.gpsolutions.hotel.dto.CreateHotelDto;
import com.gpsolutions.hotel.dto.FullHotelDto;
import com.gpsolutions.hotel.dto.HotelDto;
import com.gpsolutions.hotel.model.Parameter;
import com.gpsolutions.hotel.service.HotelService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {

  private MockMvc mockMvc;
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Mock
  private HotelService hotelService;
  @InjectMocks
  private HotelController hotelController;

  private HotelDto hotelDto;

  @BeforeEach
  void setUp(){
    hotelDto = new HotelDto(
        1L,
        "Test Name",
        "Test Description",
        "Test Brand",
        "Test Address",
        "Test Phone");
    mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
  }

  @Test
  void getAllHotels_shouldReturnListOfHotelDto() throws Exception{
    final HotelDto hotelDto = new HotelDto(
        1L,
        "Test Name",
        "Test Description",
        "Test Brand",
        "Test Address",
        "Test Phone");
    when(hotelService.getAllHotels()).thenReturn(List.of(hotelDto));

    mockMvc.perform(MockMvcRequestBuilders.get("/property-view/hotels"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
            .value(this.hotelDto.id()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
            .value(this.hotelDto.name()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].description")
            .value(this.hotelDto.description()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand")
            .value(this.hotelDto.brand()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].address")
            .value(this.hotelDto.address()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone")
            .value(this.hotelDto.phone()));
  }

  @Test
  void getFullHotelInfo_shouldReturnFullHotelDtoByHotelId() throws Exception {
    final FullHotelDto fullHotelDto = new FullHotelDto(
        1L,
        "Test Name",
        "Test Description",
        "Test Brand",
        new AddressDto(
            2L,
            "Test Street",
            "Test City",
            "Test Country",
            "Test Post Code"
        ),
        new ContactsDto(
            "Test Phone",
            "Test Email"
        ),
        new ArrivalTimeDto(
            "Test Check In",
            "Test Check Out"
        ),
        List.of("Test Amenity")
    );
    when(hotelService.getFullHotelInfo(1L)).thenReturn(fullHotelDto);

    mockMvc.perform(MockMvcRequestBuilders.get("/property-view/hotels/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id")
            .value(fullHotelDto.id()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name")
            .value(fullHotelDto.name()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description")
            .value(fullHotelDto.description()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.brand")
            .value(fullHotelDto.brand()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address.houseNumber")
            .value(fullHotelDto.address().houseNumber()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address.street")
            .value(fullHotelDto.address().street()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address.city")
            .value(fullHotelDto.address().city()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address.country")
            .value(fullHotelDto.address().country()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address.postCode")
            .value(fullHotelDto.address().postCode()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.contacts.phone")
            .value(fullHotelDto.contacts().phone()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.contacts.email")
            .value(fullHotelDto.contacts().email()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalTime.checkIn")
            .value(fullHotelDto.arrivalTime().checkIn()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalTime.checkOut")
            .value(fullHotelDto.arrivalTime().checkOut()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.amenities[0]")
            .value(fullHotelDto.amenities().getFirst()));
  }

  @Test
  void searchForHotels_shouldReturnListOfHotelDto() throws Exception {
    final HotelDto hotelDto = new HotelDto(
        1L,
        "Test Name",
        "Test Description",
        "Test Brand",
        "Test Address",
        "Test Phone");
    when(hotelService.searchForHotels(
        hotelDto.name(),
        hotelDto.brand(),
        null,
        null,
        null
    )).thenReturn(List.of(hotelDto));

    mockMvc.perform(MockMvcRequestBuilders
            .get("/property-view/search?name=Test%20Name&brand=Test%20Brand"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
            .value(this.hotelDto.id()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
            .value(this.hotelDto.name()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].description")
            .value(this.hotelDto.description()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand")
            .value(this.hotelDto.brand()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].address")
            .value(this.hotelDto.address()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone")
            .value(this.hotelDto.phone()));
  }

  @Test
  void createHotel_shouldReturnHotelDtoByCreateHotelDto() throws Exception {
    final CreateHotelDto createHotelDto = new CreateHotelDto(
        "Test Name",
        "Test Description",
        "Test Brand",
        new AddressDto(
            2L,
            "Test Street",
            "Test City",
            "Test Country",
            "230000"
        ),
        new ContactsDto(
            "Test Phone",
            "Test Email"
        ),
        new ArrivalTimeDto(
            "14:00",
            "14:00"
        ));
    final HotelDto hotelDto = new HotelDto(
        1L,
        "Test Name",
        "Test Description",
        "Test Brand",
        "Test Address",
        "Test Phone");
    when(hotelService.createHotel(any(CreateHotelDto.class))).thenReturn(hotelDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/property-view/hotels")
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createHotelDto)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id")
            .value(this.hotelDto.id()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name")
            .value(this.hotelDto.name()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description")
            .value(this.hotelDto.description()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.brand")
            .value(this.hotelDto.brand()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address")
            .value(this.hotelDto.address()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.phone")
            .value(this.hotelDto.phone()));
  }

  @Test
  void addAmenitiesToHotel_expectedStatusShouldBeOk() throws Exception {
    final List<String> amenitiesNames = List.of("Free Wi-Fi");

    mockMvc.perform(MockMvcRequestBuilders.post("/property-view/hotels/1/amenities")
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(amenitiesNames)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    verify(hotelService, times(1))
        .addAmenitiesToHotel(eq(1L), eq(amenitiesNames));
  }

  @Test
  void getHotelHistogram_shouldReturnBrandHistogram() throws Exception {
    final Map<String, Long> map = new LinkedHashMap<>();
    map.put("Test Brand", 1L);

    when(hotelService.getHotelHistogram(Parameter.BRAND)).thenReturn(map);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/property-view/histogram/brand"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$['Test Brand']")
            .value(1L));
  }
}
