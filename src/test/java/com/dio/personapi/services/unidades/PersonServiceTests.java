package com.dio.personapi.services.unidades;

import com.dio.personapi.repositories.PersonRepository;
import com.dio.personapi.services.PersonService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PersonServiceTests {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;
}
