package com.compassuol.sp.challenge.msproducts.controllers;

import com.compassuol.sp.challenge.msproducts.domain.dto.ProductResponseDTO;
import com.compassuol.sp.challenge.msproducts.exceptions.NotFound;
import com.compassuol.sp.challenge.msproducts.services.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static com.compassuol.sp.challenge.msproducts.commom.ProductConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;
    @Test
    void getAllProductsReturnProductsList() {
        when(productService.getAll()).thenReturn(PRODUCT_LIST);

        ResponseEntity<List<ProductResponseDTO>> sut = productController.getAll();

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).hasSize(2);
        assertThat(sut.getBody().get(0)).isEqualTo(PRODUCT_RES_DTO1);
    }

    @Test
    void getAllProductsReturnEmptyList() {
        when(productService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProductResponseDTO>> sut = productController.getAll();

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).hasSize(0);
    }

    @Test
    void createProductWithValidDataReturnProduct() {
        when(productService.saveProduct(PRODUCT_REQ_DTO)).thenReturn(PRODUCT_RES_DTO1);

        ResponseEntity<ProductResponseDTO> sut = productController.createProduct(PRODUCT_REQ_DTO);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(sut.getBody()).isEqualTo(PRODUCT_RES_DTO1);
    }

    @Test
    void createProductWithInvalidDataThrowException() {
        when(productService.saveProduct(PRODUCT_REQ_DTO)).thenThrow(ConstraintViolationException.class);

        assertThatThrownBy(
                () -> productController.createProduct(PRODUCT_REQ_DTO)
        ).isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    void updateProductWithValidDataReturnProduct(){
        when(productService.updateProduct(1L,PRODUCT_REQ_DTO)).thenReturn(PRODUCT_RES_DTO1);

        ResponseEntity<ProductResponseDTO> sut = productController.updateProduct(1L, PRODUCT_REQ_DTO);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(PRODUCT_RES_DTO1);
    }
    @Test
    void updateProductWithInvalidDataThrowException(){
        when(productService.updateProduct(1L,PRODUCT_REQ_DTO)).thenThrow(ConstraintViolationException.class);

        assertThatThrownBy(
                () -> productController.updateProduct(1L,PRODUCT_REQ_DTO)
        ).isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    void updateProductWithNonExistentIdThrowException(){
        when(productService.updateProduct(1L,PRODUCT_REQ_DTO)).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> productController.updateProduct(1L,PRODUCT_REQ_DTO)
        ).isInstanceOf(NotFound.class);
    }
}
