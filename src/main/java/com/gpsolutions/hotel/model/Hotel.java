package com.gpsolutions.hotel.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "hotel", schema = "public")
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

  @Column(name = "description")
  private String description;

  @Column(name = "brand", nullable = false)
  private String brand;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "houseNumber", column = @Column(name = "house_number")),
      @AttributeOverride(name = "street", column = @Column(name = "street")),
      @AttributeOverride(name = "city", column = @Column(name = "city")),
      @AttributeOverride(name = "country", column = @Column(name = "country")),
      @AttributeOverride(name = "postCode", column = @Column(name = "post_code")),
  })
  private Address address;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "phone", column = @Column(name = "phone")),
      @AttributeOverride(name = "email", column = @Column(name = "email")),
  })
  private Contacts contacts;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "checkIn", column = @Column(name = "check_in")),
      @AttributeOverride(name = "checkOut", column = @Column(name = "check_out")),
  })
  private ArrivalTime arrivalTime;

  @ManyToMany
  @JoinTable(name = "hotel_amenity",
  joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "id"))
  private List<Amenity> amenities;
}
