package com.compassuol.sp.challenge.msproducts.controllers;

import com.compassuol.sp.challenge.msproducts.exceptions.NotFound;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping("**")
    public void catchInvalidEndpointAccess() {
        throw new NotFound("Page not found");
    }
}
