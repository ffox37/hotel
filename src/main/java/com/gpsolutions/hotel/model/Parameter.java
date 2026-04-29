package com.gpsolutions.hotel.model;

import com.gpsolutions.hotel.exceptions.BadRequestException;
import java.util.Objects;

public enum Parameter {

  BRAND("brand"),
  CITY("city"),
  COUNTRY("country"),
  AMENITIES("amenities");

  private final String code;

  Parameter(String code) {
    this.code = code;
  }

  private String getCode(Parameter parameter){
    return parameter.code;
  }

  private static Parameter getParameter(String code) {
    for(Parameter param : values()){
      if(Objects.equals(param.code, code)){
        return param;
      }
    }
    throw new BadRequestException("Wrong parameter!");
  }
}
