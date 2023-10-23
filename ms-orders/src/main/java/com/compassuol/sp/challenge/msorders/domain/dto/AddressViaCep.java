package com.compassuol.sp.challenge.msorders.domain.dto;

public record AddressViaCep(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) { }
