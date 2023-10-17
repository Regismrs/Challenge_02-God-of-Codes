package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressRequestDto {

    private String street;
    private String number;
    private String postalCode;

}
