package com.compassuol.sp.challenge.msproducts.services;

import com.compassuol.sp.challenge.msproducts.models.dtos.ProductResponseDto;
import com.compassuol.sp.challenge.msproducts.models.entities.Product;
import com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
// constants
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.compassuol.sp.challenge.msproducts.commom.ProductConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void getProductsReturnsEmptyList() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductResponseDto> sut = productService.getAll();

        assertThat(sut).isEmpty();
    }

    @Test
    void getProductsReturnsAllProducts() {
        List<Product> products = new ArrayList<Product>(){
            {
                add(PRODUCT_WITH_ID);
            }
        };
        when(productRepository.findAll()).thenReturn(products);
        List<ProductResponseDto> sut = productService.getAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);

        // comparacao campo a campo pq product <> productResponseDto
        assertThat(sut.get(0)).isInstanceOf(ProductResponseDto.class);
        assertThat(sut.get(0))
                .usingRecursiveComparison()
                .isEqualTo(PRODUCT_WITH_ID);

    }

    @Test
    void createProductWithValidDataReturnProduct() {
        ProductResponseDto expected =
                new ProductResponseDto(1L, "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

        when(productRepository.save(any(Product.class))).thenReturn(PRODUCT_WITH_ID);

        ProductResponseDto sut = productService.saveProduct(PRODUCT_REQ_DTO);

        assertThat(sut).isInstanceOf(ProductResponseDto.class);
        assertThat(sut)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void createProductWithInvalidDataThrowsException() {
        when(productRepository.save(INVALID_PRODUCT)).thenThrow(RuntimeException.class);
        assertThatThrownBy(
                () -> productService.saveProduct(PRODUCT_REQ_DTO)
            ).isInstanceOf(RuntimeException.class);
    }

}