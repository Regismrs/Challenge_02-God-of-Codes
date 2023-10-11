package com.compassuol.sp.challenge.msproducts.mapper;

import com.compassuol.sp.challenge.msproducts.models.dtos.ProductRequestDto;
import com.compassuol.sp.challenge.msproducts.models.dtos.ProductResponseDto;
import com.compassuol.sp.challenge.msproducts.models.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

        public static Product toModel(ProductRequestDto createProductDto){
            Product product = new Product();
            product.setName(createProductDto.getName());
            product.setDescription(createProductDto.getDescription());
            product.setValue(createProductDto.getValue());
            return product;
        }

        public static ProductResponseDto toDto(Product product){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getId());
            productResponseDto.setName(product.getName());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setValue(product.getValue());
            return productResponseDto;
        }

        public static List<ProductResponseDto> toListDto(List<Product> productList){
            List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for (Product product : productList){
                productResponseDtos.add(toDto(product));
            }
            return productResponseDtos;
        }
}
