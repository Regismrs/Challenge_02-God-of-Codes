package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.ProductMicroservice;
import com.compassuol.sp.challenge.msorders.domain.dto.ProductRequest;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.exceptions.BusinessException;
import com.compassuol.sp.challenge.msorders.exceptions.NotFound;
import com.compassuol.sp.challenge.msorders.mapper.OrderMapper;
import com.compassuol.sp.challenge.msorders.services.third.MsProductsService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final MsProductsService productsAPI;

    public ProductService(MsProductsService productsAPI) {
        this.productsAPI = productsAPI;
    }

    public List<OrderProduct> completeProductsDataWithAPI(OrderRequest orderRequest) {
        return retrieveProductsFromAPI(orderRequest.getProductsRequest());

    }

    private List<OrderProduct> retrieveProductsFromAPI(List<ProductRequest> request) {
        return request.stream()
                .map(productRequest -> OrderMapper.requestPlusProductMicroserviceToModel(
                        productRequest,
                        retrieveProductFromAPI(productRequest))
                ).toList();
    }

    private ProductMicroservice retrieveProductFromAPI(ProductRequest productRequest)
            throws BusinessException, NotFound, RuntimeException {

        try {
            return productsAPI.getProductById(productRequest.getProductId());

        } catch (FeignException ex) {
            switch (ex.status()) {
                case 400:
                    throw new BusinessException("Invalid Product id.");
                case 404:
                    throw new NotFound("Not Found Product id " + productRequest.getProductId());
                default:
                    throw new RuntimeException("Can't connect on MS-Products server.");
            }
        }
    }
}
