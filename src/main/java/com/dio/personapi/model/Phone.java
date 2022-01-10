package com.dio.personapi.model;

import com.dio.personapi.dto.PhoneDto;
import com.dio.personapi.model.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Phones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private PhoneType phoneType;
    @Column(nullable = false)
    private String phoneNumber;

    public Phone(PhoneDto entity) {
        id = entity.getId();
        phoneType = entity.getPhoneType();
        phoneNumber = entity.getPhoneNumber();
    }
}
