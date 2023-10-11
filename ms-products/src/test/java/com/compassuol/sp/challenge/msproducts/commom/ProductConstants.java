package com.compassuol.sp.challenge.msproducts.commom;

import com.compassuol.sp.challenge.msproducts.models.dtos.ProductRequestDto;
import com.compassuol.sp.challenge.msproducts.models.dtos.ProductResponseDto;
import com.compassuol.sp.challenge.msproducts.models.entities.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProductConstants {
    public static final Product INVALID_PRODUCT = new Product();
    public static final Product PRODUCT =
            new Product(
                    "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

    public static final Product PRODUCT_WITH_ID =
            new Product(
                    1L, "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));

    public static final ProductRequestDto PRODUCT_REQ_DTO =
            new ProductRequestDto(
                    "Produto 1", "Description product 1", BigDecimal.valueOf(1.11));
    public static final ProductResponseDto PRODUCT_RES_DTO1 =
            new ProductResponseDto(
                    1L, "Produto 1", "description produto 1", BigDecimal.valueOf(1.11));
    public static final ProductResponseDto PRODUCT_RES_DTO2 =
            new ProductResponseDto(
                    2L, "Produto 2", "description produto 2", BigDecimal.valueOf(2.22));


    public static final List<ProductResponseDto> PRODUCT_LIST =
            Arrays.asList(PRODUCT_RES_DTO1, PRODUCT_RES_DTO2);
}
