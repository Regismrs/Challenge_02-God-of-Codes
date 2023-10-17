package com.compassuol.sp.challenge.msproducts.mapper;

import com.compassuol.sp.challenge.msproducts.domain.dto.ProductRequestDTO;
import com.compassuol.sp.challenge.msproducts.domain.dto.ProductResponseDTO;
import com.compassuol.sp.challenge.msproducts.domain.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

        public static Product toModel(ProductRequestDTO createProductDTO){
            Product product = new Product();
            product.setName(createProductDTO.getName());
            product.setDescription(createProductDTO.getDescription());
            product.setValue(createProductDTO.getValue());
            return product;
        }

        public static ProductResponseDTO toDTO(Product product){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(product.getId());
            productResponseDTO.setName(product.getName());
            productResponseDTO.setDescription(product.getDescription());
            productResponseDTO.setValue(product.getValue());
            return productResponseDTO;
        }

        public static List<ProductResponseDTO> toListDTO(List<Product> productList){
            List<ProductResponseDTO> productsResponseDTO = new ArrayList<>();
            for (Product product : productList){
                productsResponseDTO.add(toDTO(product));
            }
            return productsResponseDTO;
        }

        public static Product toUpdate(Product product, ProductRequestDTO requestDTO) {
            product.setName(requestDTO.getName());
            product.setDescription(requestDTO.getDescription());
            product.setValue(requestDTO.getValue());

            return product;
        }
}
