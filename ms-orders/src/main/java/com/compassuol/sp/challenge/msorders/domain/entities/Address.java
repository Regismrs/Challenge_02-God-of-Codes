package com.compassuol.sp.challenge.msorders.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@Valid
public class Address {
    private String street;
    @Column(nullable = false)
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;
}
