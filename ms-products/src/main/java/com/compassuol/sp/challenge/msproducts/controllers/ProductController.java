package com.compassuol.sp.challenge.msproducts.controllers;

import com.compassuol.sp.challenge.msproducts.models.dtos.ProductRequestDto;
import com.compassuol.sp.challenge.msproducts.models.dtos.ProductResponseDto;
import com.compassuol.sp.challenge.msproducts.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateCar(@Valid @PathVariable(value = "id") Long id,
                                            @RequestBody ProductRequestDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDto));
    }

}
