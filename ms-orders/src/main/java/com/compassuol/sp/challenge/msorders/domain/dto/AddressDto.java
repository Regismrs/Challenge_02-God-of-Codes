package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String postalCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
}
