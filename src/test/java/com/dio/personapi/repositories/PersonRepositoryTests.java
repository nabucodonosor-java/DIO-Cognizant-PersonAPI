package com.dio.personapi.repositories;

import com.dio.personapi.factories.PersonFactory;
import com.dio.personapi.model.Person;
import com.dio.personapi.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository repository;

    private long existingId;
    private long nonExistingId;
    private Person person;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        person = PersonFactory.createPreson();
    }

    @Test
    public void findByIdShouldReturnPersonWhenIdExists() {
        Optional<Person> obj = repository.findById(existingId);
        Assertions.assertTrue(obj.isPresent());
    }

    @Test
    public void findByIdShouldNotReturnPersonWhenIdNonExists() {
        Optional<Person> obj = repository.findById(nonExistingId);
        Assertions.assertTrue(obj.isEmpty());
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        person.setId(null);

        person = repository.save(person);
        Assertions.assertNotNull(person.getId());
        Assertions.assertEquals(7L, person.getId());
    }

    @Test
    public void deleteShouldDeletePersonWhenIdExists() {
        repository.deleteById(existingId);
        Optional<Person> obj = repository.findById(existingId);
        Assertions.assertTrue(obj.isEmpty());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }
}
