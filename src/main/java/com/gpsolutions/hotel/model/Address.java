package com.gpsolutions.hotel.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class Address {

  private Long houseNumber;
  private String street;
  private String city;
  private String country;
  private String postCode;
}
