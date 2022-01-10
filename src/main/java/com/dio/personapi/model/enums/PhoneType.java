package com.dio.personapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhoneType {

    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial");

    private final String description;
}
