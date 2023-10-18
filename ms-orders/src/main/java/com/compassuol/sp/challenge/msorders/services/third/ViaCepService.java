package com.compassuol.sp.challenge.msorders.services.third;

import com.compassuol.sp.challenge.msorders.domain.dto.AddressViaCepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "http://viacep.com.br/ws")
public interface ViaCepService {
    @GetMapping("/{postal}/json")
    public AddressViaCepDto getAddressByPostalCode(@PathVariable("postal") String postalCode);
}
