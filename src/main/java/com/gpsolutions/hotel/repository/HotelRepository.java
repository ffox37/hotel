package com.gpsolutions.hotel.repository;

import com.gpsolutions.hotel.model.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long>,
    JpaSpecificationExecutor<Hotel> {

  @Query("SELECT h.brand, COUNT(h) FROM Hotel h GROUP BY h.brand")
  List<Object[]> countAndGetNumberOfHotelsForAllBrands();

  @Query("SELECT h.address.city, COUNT(h) FROM Hotel h GROUP BY h.address.city")
  List<Object[]> countAndGetNumberOfHotelsForAllCities();

  @Query("SELECT h.address.country, COUNT(h) FROM Hotel h GROUP BY h.address.country")
  List<Object[]> countAndGetNumberOfHotelsForAllCountries();

  @Query("SELECT a.name, COUNT(h) FROM Hotel h JOIN h.amenities a GROUP BY a.name")
  List<Object[]> countAndGetNumberOfHotelsForAllAmenities();
}
