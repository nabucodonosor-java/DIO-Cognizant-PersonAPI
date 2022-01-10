package com.dio.personapi.factories;

import com.dio.personapi.dto.PersonDto;
import com.dio.personapi.model.Person;

import java.time.LocalDate;

public class PersonFactory {

    public static Person createPreson() {
        Person person = new Person();
        person.setFirstName("Darth");
        person.setLastName("Vader");
        person.setCpf(777);
        person.setBirthDate(LocalDate.now());
        return person;
    }

    public static PersonDto createPersonDto() {
        return new PersonDto(createPreson());
    }
}
