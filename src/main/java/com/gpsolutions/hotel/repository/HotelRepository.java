package com.gpsolutions.hotel.repository;

import com.gpsolutions.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HotelRepository extends JpaRepository<Hotel, Long>,
    JpaSpecificationExecutor<Hotel> {

}
