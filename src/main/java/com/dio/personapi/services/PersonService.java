package com.dio.personapi.services;

import com.dio.personapi.dto.PersonDto;
import com.dio.personapi.model.Person;
import com.dio.personapi.model.Phone;
import com.dio.personapi.repositories.PersonRepository;
import com.dio.personapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional(readOnly = true)
    public Page<PersonDto> findAll(PageRequest pageable) {
        Page<Person> page = repository.findAll(pageable);
        return PersonDto.converter(page);
    }

    @Transactional(readOnly = true)
    public PersonDto findById(Long id) {
        Optional<Person> obj = repository.findById(id);
        Person entity = obj.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
        return new PersonDto(entity);
    }

    @Transactional
    public PersonDto insert(PersonDto dto) {
        Person entity = new Person();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PersonDto(entity);
    }

    @Transactional
    public PersonDto update(Long idPerson, PersonDto dto) {
        try {
            Person entity = repository.getOne(idPerson);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PersonDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pessoa não encontrada!");
        }
    }

    public void delete(Long id) {
        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Pessoa não encontrada!");
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Violação no DB");
        }
    }

    private void copyDtoToEntity(PersonDto dto, Person entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.getPhones().clear();
        dto.getPhones().forEach(phone -> entity.getPhones().add(new Phone(phone)));
    }

}
