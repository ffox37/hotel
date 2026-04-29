package com.gpsolutions.hotel.model;

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

  private static Parameter getParameter(String code) throws Exception{
    for(Parameter param : values()){
      if(Objects.equals(param.code, code)){
        return param;
      }
    }
    //TODO заменить на настоящую ошибку
    throw new Exception("Bad Request");
  }
}
