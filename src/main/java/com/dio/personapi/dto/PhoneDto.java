package com.dio.personapi.dto;

import com.dio.personapi.model.Phone;
import com.dio.personapi.model.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

    private Long id;
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    @NotBlank(message = "CAMPO OBRIGATÓRIO!")
    private String phoneNumber;

    public PhoneDto(Phone entity) {
        id = entity.getId();
        phoneType = entity.getPhoneType();
        phoneNumber = entity.getPhoneNumber();
    }
}
