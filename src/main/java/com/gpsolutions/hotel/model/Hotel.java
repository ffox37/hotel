package com.gpsolutions.hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotel", schema = "hotel")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "descripion")
  private String description;

  @Column(name = "brand", nullable = false)
  private String brand;

  @Column(name = "house_number", nullable = false)
  private Long houseNumber;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "post_code", nullable = false)
  private String postCode;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "check_in", nullable = false)
  private String checkIn;

  @Column(name = "check_out", nullable = false)
  private String checkOut;

  @ManyToMany
  @JoinTable(name = "hotel_amenity",
  joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "id"))
  private List<Amenity> amenities;
}
