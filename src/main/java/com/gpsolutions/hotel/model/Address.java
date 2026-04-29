package com.gpsolutions.hotel.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

  private Long houseNumber;
  private String street;
  private String city;
  private String country;
  private String postCode;

  @Override
  public String toString(){
    return houseNumber + " "
        + street + " "
        + city + " "
        + country + " "
        + postCode;
  }
}
