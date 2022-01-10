package com.dio.personapi.dto;

import com.dio.personapi.model.Person;
import lombok.*;
import org.springframework.data.domain.Page;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Long id;
    @NotBlank(message = "CAMPO OBRIGATÓRIO!")
    private String firstName;
    @NotBlank(message = "CAMPO OBRIGATÓRIO!")
    private String lastName;
    @NotNull(message = "CAMPO OBRIGATÓRIO!")
    private Integer cpf;
    private LocalDate birthDate;

    @Valid
    @NotEmpty(message = "Ao menos 1 (um) telefone deve ser cadastrado!")
    private List<PhoneDto> phones = new ArrayList<>();

    public PersonDto(Person entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        cpf = entity.getCpf();
        birthDate = entity.getBirthDate();
        entity.getPhones().forEach(phone -> this.phones.add(new PhoneDto(phone)));
    }

    public static Page<PersonDto> converter(Page<Person> page) {
        return page.map(PersonDto::new);
    }
}
