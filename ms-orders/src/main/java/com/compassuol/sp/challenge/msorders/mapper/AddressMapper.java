package com.compassuol.sp.challenge.msorders.mapper;

import com.compassuol.sp.challenge.msorders.domain.dto.AddressRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.AddressViaCep;
import com.compassuol.sp.challenge.msorders.domain.entities.Address;

public class AddressMapper {

    public static Address requestPlusViaCepToModel(
            AddressRequest request,
            AddressViaCep viaCep) {

        Address address = new Address();
        // request
        address.setNumber(request.getNumber());
        address.setComplement(request.getStreet());
        address.setPostalCode(request.getPostalCode());
        // + viacep
        address.setCity(viaCep.localidade());
        address.setStreet(viaCep.logradouro());
        address.setState(viaCep.uf());

        return address;
    }
}
