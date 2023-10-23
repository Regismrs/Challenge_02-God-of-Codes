package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;
}
