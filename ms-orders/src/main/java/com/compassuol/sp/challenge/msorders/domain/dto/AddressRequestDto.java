package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressRequestDto {

    private String street;
    private Integer number;
    private String postalCode;

}
