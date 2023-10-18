package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AddressResponseDto {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;
}
