package com.compassuol.sp.challenge.msproducts.controllers;

import com.compassuol.sp.challenge.msproducts.models.entities.Product;
import com.compassuol.sp.challenge.msproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
