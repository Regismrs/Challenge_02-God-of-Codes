package com.compassuol.sp.challenge.msorders.domain.dto;

public record AddressViaCepDto(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) { }
