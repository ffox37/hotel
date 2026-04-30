package com.gpsolutions.hotel.mapper;

import com.gpsolutions.hotel.dto.ContactsDto;
import com.gpsolutions.hotel.model.Contacts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactsMapperTest {

  private final ContactsMapper contactsMapper = new ContactsMapperImpl();

  @Test
  void shouldMapContactsDtoToContactsEntity(){
    final ContactsDto contactsDto = new ContactsDto(
        "+327445555555",
        "testemail@gmail.com");
    final Contacts contacts = contactsMapper.fromDto(contactsDto);
    Assertions.assertEquals(contactsDto.phone(), contacts.getPhone());
    Assertions.assertEquals(contactsDto.email(), contacts.getEmail());
  }

  @Test
  void shouldMapContactsEntityToContactsDto(){
    final Contacts contacts = new Contacts(
        "+327445555555",
        "testemail@gmail.com");
    final ContactsDto contactsDto = contactsMapper.toDto(contacts);
    Assertions.assertEquals(contacts.getPhone(), contactsDto.phone());
    Assertions.assertEquals(contacts.getEmail(), contactsDto.email());
  }
}
