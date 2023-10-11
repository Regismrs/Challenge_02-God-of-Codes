package com.compassuol.sp.challenge.msproducts.services;

import com.compassuol.sp.challenge.msproducts.exceptions.NotFound;
import com.compassuol.sp.challenge.msproducts.models.dtos.ProductRequestDto;
import com.compassuol.sp.challenge.msproducts.models.dtos.ProductResponseDto;
import com.compassuol.sp.challenge.msproducts.mapper.ProductMapper;
import com.compassuol.sp.challenge.msproducts.models.entities.Product;
import com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDto> getAll(){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = ProductMapper.toListDto(products);

        return productResponseDtos;
    }

    public ProductResponseDto saveProduct(ProductRequestDto productDto) {
        Product product = ProductMapper.toModel(productDto);
        Product productSaved = productRepository.save(product);

        return ProductMapper.toDto(productSaved);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()
                -> new NotFound("Product not found"));
        productRepository.delete(product);
    }
}
