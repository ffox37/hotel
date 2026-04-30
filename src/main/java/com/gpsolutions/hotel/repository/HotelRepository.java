package com.gpsolutions.hotel.repository;

import com.gpsolutions.hotel.model.Amenity;
import com.gpsolutions.hotel.model.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Long>,
    JpaSpecificationExecutor<Hotel> {

  @Query("SELECT DISTINCT h.brand FROM Hotel h")
  List<String> getAllBrands();

  Long countHotelsByBrand(String brand);

  @Query("SELECT DISTINCT h.address.city FROM Hotel h")
  List<String> getAllCities();

  @Query("SELECT COUNT(*) FROM Hotel h WHERE h.address.city = :city")
  Long countHotelsByCity(@Param("city") String city);

  @Query("SELECT DISTINCT h.address.country FROM Hotel h")
  List<String> getAllCountries();

  @Query("SELECT COUNT(*) FROM Hotel h WHERE h.address.country = :country")
  Long countHotelsByCountry(@Param("country") String country);

  @Query("SELECT DISTINCT h.amenities FROM Hotel h")
  List<Amenity> getAllAmenities();

  @Query("SELECT COUNT(*) FROM Hotel h WHERE :amenity MEMBER OF h.amenities")
  Long countHotelsByAmenity(@Param("amenity") Amenity amenity);
}
