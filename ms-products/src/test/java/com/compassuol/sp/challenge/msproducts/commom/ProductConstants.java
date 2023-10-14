package com.compassuol.sp.challenge.msproducts.commom;

import com.compassuol.sp.challenge.msproducts.domain.dto.ProductRequestDTO;
import com.compassuol.sp.challenge.msproducts.domain.dto.ProductResponseDTO;
import com.compassuol.sp.challenge.msproducts.domain.entities.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProductConstants {
    public static final Product INVALID_PRODUCT = new Product();

    public static final Product EMPTY_PRODUCT_WITH_ID = new Product(1L);

    public static final Product EMPTY_PRODUCT = new Product("", "", null);
    public static final Product PRODUCT =
            new Product(
                    "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

    public static final Product PRODUCT_WITH_ID =
            new Product(
                    1L, "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

    public static final ProductRequestDTO PRODUCT_REQ_DTO =
            new ProductRequestDTO(
                    "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

    public static final ProductResponseDTO PRODUCT_RES_DTO1 =
            new ProductResponseDTO(
                    1L, "Produto 1", "description produto 1", BigDecimal.valueOf(1.11));

    public static final ProductResponseDTO PRODUCT_RES_DTO2 =
            new ProductResponseDTO(
                    2L, "Produto 2", "description produto 2", BigDecimal.valueOf(2.22));


    public static final List<ProductResponseDTO> PRODUCT_LIST =
            Arrays.asList(PRODUCT_RES_DTO1, PRODUCT_RES_DTO2);
}
