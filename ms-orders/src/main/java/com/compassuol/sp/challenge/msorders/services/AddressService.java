package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.AddressRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.AddressViaCep;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequest;
import com.compassuol.sp.challenge.msorders.domain.entities.Address;
import com.compassuol.sp.challenge.msorders.exceptions.BusinessException;
import com.compassuol.sp.challenge.msorders.exceptions.NotFound;
import com.compassuol.sp.challenge.msorders.mapper.AddressMapper;
import com.compassuol.sp.challenge.msorders.services.third.ViaCepService;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final ViaCepService viaCepService;

    public AddressService(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    public Address completeAddressWithAPI(OrderRequest orderRequest) {
        AddressRequest request = orderRequest.getAddressRequest();

        return AddressMapper.requestPlusViaCepToModel(
                        request,
                        getPostalCodeDetails(request.getPostalCode()));
    }

    private AddressViaCep getPostalCodeDetails (String postalCode)
            throws BusinessException, NotFound, RuntimeException {

        try {
            return viaCepService
                    .getAddressByPostalCode(postalCode);
        } catch (FeignException ex) {
            switch (ex.status()) {
                case 400:
                    throw new BusinessException("Invalid postal code.");
                case 404:
                    throw new NotFound("Not Found postal code.");
                default:
                    throw new RuntimeException("Can't connect on ViaCep server.");
            }
        }
    }
}
