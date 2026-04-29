package com.gpsolutions.hotel.repository;

import com.gpsolutions.hotel.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

  boolean existsByName(String name);

  Amenity findByName(String name);
}
