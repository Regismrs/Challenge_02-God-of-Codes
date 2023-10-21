package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.Getter;
import lombok.Setter;

public record AddressViaCep(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) { }
