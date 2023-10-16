package com.compassuol.sp.challenge.msproducts.services;

import com.compassuol.sp.challenge.msproducts.domain.dto.ProductRequestDTO;
import com.compassuol.sp.challenge.msproducts.domain.dto.ProductResponseDTO;
import com.compassuol.sp.challenge.msproducts.domain.entities.Product;
import com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.compassuol.sp.challenge.msproducts.commom.ProductConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void getProductsReturnsEmptyList() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductResponseDTO> sut = productService.getAll();

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
        List<ProductResponseDTO> sut = productService.getAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);

        // comparacao campo a campo pq product <> productResponseDto
        assertThat(sut.get(0)).isInstanceOf(ProductResponseDTO.class);
        assertThat(sut.get(0))
                .usingRecursiveComparison()
                .isEqualTo(PRODUCT_WITH_ID);

    }

    @Test
    void createProductWithValidDataReturnProduct() {
        ProductResponseDTO expected =
                new ProductResponseDTO(1L, "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

        when(productRepository.save(any(Product.class))).thenReturn(PRODUCT_WITH_ID);

        ProductResponseDTO sut = productService.saveProduct(PRODUCT_REQ_DTO);

        assertThat(sut).isInstanceOf(ProductResponseDTO.class);
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
    @Test
    void updateProductWithValidDataReturnProduct() {
        ProductRequestDTO productDTO = new ProductRequestDTO();
        productDTO.setName("Produto Atualizado");
        productDTO.setDescription("Descrição Atualizada");
        productDTO.setValue(BigDecimal.TEN);

        Product existingProduct = EMPTY_PRODUCT;
        existingProduct.setId(1L);
        existingProduct.setName("Produto Existente");
        existingProduct.setDescription("Descrição Existente");
        existingProduct.setValue(BigDecimal.valueOf(5.0));

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        ProductResponseDTO updatedProduct = productService.updateProduct(1L, productDTO);

        assertThat(updatedProduct).isNotNull();

        assertThat(updatedProduct.getName()).isEqualTo("Produto Atualizado");
        assertThat(updatedProduct.getDescription()).isEqualTo("Descrição Atualizada");
        assertThat(updatedProduct.getValue()).isEqualByComparingTo(BigDecimal.TEN);
    }
    @Test
    public void updateProductWithInvalidDataThrowsException() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(PRODUCT_WITH_ID));

        ProductRequestDTO productDTO = new ProductRequestDTO();

        assertThatThrownBy(
                () -> productService.updateProduct(1L, productDTO )
        ).isInstanceOf(RuntimeException.class);
    }
    @Test
    public void updateProductWithDuplicatedNameThrowsException() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Produto Existente");
        existingProduct.setDescription("Descrição Existente");
        existingProduct.setValue(BigDecimal.valueOf(5.0));

        Product newProduct = new Product();
        newProduct.setId(2L);
        newProduct.setName("Produto Existente"); // Mesmo nome do produto existente
        newProduct.setDescription("Nova Descrição");
        newProduct.setValue(BigDecimal.valueOf(10.0));

        when(productRepository.findById(2L)).thenReturn(Optional.of(newProduct));
        when(productRepository.save(newProduct)).thenThrow(DataIntegrityViolationException.class);

        assertThatThrownBy(
                () -> productService.updateProduct(2L, new ProductRequestDTO())
        ).isInstanceOf(DataIntegrityViolationException.class);
    }
}