package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.ContactsDto;
import com.gpsolutions.hotel.model.Contacts;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface ContactsMapper {

  Contacts fromDto(ContactsDto contactsDto);

  ContactsDto toDto(Contacts contacts);
}
