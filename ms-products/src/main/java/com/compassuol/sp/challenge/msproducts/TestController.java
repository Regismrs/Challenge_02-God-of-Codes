package com.compassuol.sp.challenge.msproducts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public String getTest() {
        return "Testando controller";
    }

}
