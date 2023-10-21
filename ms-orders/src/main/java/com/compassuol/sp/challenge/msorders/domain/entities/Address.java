package com.compassuol.sp.challenge.msorders.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    @Column(nullable = false)
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;
}
