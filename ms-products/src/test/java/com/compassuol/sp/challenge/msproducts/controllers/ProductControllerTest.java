package com.compassuol.sp.challenge.msproducts.controllers;

import com.compassuol.sp.challenge.msproducts.domain.dto.ProductResponseDTO;
import com.compassuol.sp.challenge.msproducts.exceptions.NotFound;
import com.compassuol.sp.challenge.msproducts.services.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.compassuol.sp.challenge.msproducts.commom.ProductConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

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
        assertThat(sut.getBody()).isEmpty();
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
    void updateProductWithValidDataReturnProduct() {
        when(productService.updateProduct(1L, PRODUCT_REQ_DTO)).thenReturn(PRODUCT_RES_DTO1);

        ResponseEntity<ProductResponseDTO> sut = productController.updateProduct(1L, PRODUCT_REQ_DTO);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(PRODUCT_RES_DTO1);
    }

    @Test
    void updateProductWithInvalidDataThrowException() {
        when(productService.updateProduct(1L, PRODUCT_REQ_DTO)).thenThrow(ConstraintViolationException.class);

        assertThatThrownBy(
                () -> productController.updateProduct(1L, PRODUCT_REQ_DTO)
        ).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void updateProductWithNonExistentIdThrowException() {
        when(productService.updateProduct(1L, PRODUCT_REQ_DTO)).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> productController.updateProduct(1L, PRODUCT_REQ_DTO)
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void removeProductWithExistentId() {
        Long id = 1L;
        doNothing().when(productService).deleteProduct(id);
        ResponseEntity<Object> response = productController.deleteProduct(id);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Product delected sucessfully", response.getBody());
        verify(productService).deleteProduct(id);
    }

    @Test
    void removeProductWithNonExistentId() {
        Long id = 1L;
        doThrow(new NotFound("Id non existent")).when(productService).deleteProduct(id);

        assertThatThrownBy(
                () -> productController.deleteProduct(id)
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void ProductByIdWithNonExistent() {
        when(productService.findById(1L)).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> productController.getProduct(1L)
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void ProductByIdWithExistent() {
        when(productService.findById(1L)).thenReturn(PRODUCT_RES_DTO1);

        ResponseEntity<ProductResponseDTO> sut = productController.getProduct(1L);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(PRODUCT_RES_DTO1);
    }
}
