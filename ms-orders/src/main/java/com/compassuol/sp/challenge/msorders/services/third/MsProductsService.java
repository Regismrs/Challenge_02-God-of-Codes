package com.compassuol.sp.challenge.msorders.services.third;

import com.compassuol.sp.challenge.msorders.domain.dto.ProductMicroservice;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-products", url = "http://localhost:8081/products")
public interface MsProductsService {
    @GetMapping("/{id}")
    public ProductMicroservice getProductById(@PathVariable("id") Long id);
}
