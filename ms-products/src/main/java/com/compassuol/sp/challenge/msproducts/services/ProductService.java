package com.compassuol.sp.challenge.msproducts.services;

import com.compassuol.sp.challenge.msproducts.dtos.ProductResponseDto;
import com.compassuol.sp.challenge.msproducts.mapper.ProductMapper;
import com.compassuol.sp.challenge.msproducts.models.entities.Product;
import com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDto> getAll(){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = ProductMapper.toProductListDto(products);
        return productResponseDtos;
    }
}
