package com.gpsolutions.hotel.repository;

import com.gpsolutions.hotel.model.Amenity;
import com.gpsolutions.hotel.model.Hotel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class HotelSpecification {
  private HotelSpecification() {}

  public static Specification<Hotel> withName(final String name){
    return (root, query, criteriaBuilder) ->
        name == null ?
            null :
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + name.toLowerCase() + "%");
  }

  public static Specification<Hotel> withBrand(final String brand){
    return (root, query, criteriaBuilder) ->
        brand == null ?
            null :
            criteriaBuilder.equal(root.get("brand"), brand);
  }

  public static Specification<Hotel> withCity(final String city){
    return (root, query, criteriaBuilder) ->
        city == null ?
            null :
            criteriaBuilder.equal(root.get("address").get("city"), city);
  }

  public static Specification<Hotel> withCountry(final String country){
    return (root, query, criteriaBuilder) ->
        country == null ?
            null :
            criteriaBuilder.equal(root.get("address").get("country"), country);
  }

  public static Specification<Hotel> withAmenities(final List<Amenity> amenities){
    return (root, query, criteriaBuilder) -> {
      Predicate result = criteriaBuilder.conjunction();
      if (amenities == null || amenities.isEmpty()) {
        return result;
      }
      else {
        for(Amenity amenity : amenities){
          Join<Hotel, Amenity> join = root.join("amenities");
          result = criteriaBuilder.and(
              result,
              criteriaBuilder.equal(join.get("id"), amenity.getId()));
        }
        return result;
      }
    };
  }

  public static Specification<Hotel> withFilters(
      final String name,
      final String brand,
      final String city,
      final String country,
      final List<Amenity> amenities){
    return Specification.where(withName(name))
        .and(withBrand(brand))
        .and(withCity(city))
        .and(withCountry(country))
        .and(withAmenities(amenities));
  }
}
