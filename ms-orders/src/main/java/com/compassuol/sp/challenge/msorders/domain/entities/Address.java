package com.compassuol.sp.challenge.msorders.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Address {
    private String street;
    private int number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;
}
