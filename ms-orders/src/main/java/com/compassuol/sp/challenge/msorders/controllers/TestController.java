package com.compassuol.sp.challenge.msorders.controllers;

import com.compassuol.sp.challenge.msorders.services.third.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class TestController {
    @Autowired
    private ViaCepService addressViaCepService;

    @GetMapping("/cep/{postal}")
    public ResponseEntity<Object> getCep(@PathVariable("postal") String cep) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressViaCepService.getAddressByPostalCode(cep));
    }
}
