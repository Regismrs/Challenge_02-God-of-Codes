package com.compassuol.sp.challenge.msproducts.services;

import com.compassuol.sp.challenge.msproducts.domain.dto.ProductRequestDTO;
import com.compassuol.sp.challenge.msproducts.domain.dto.ProductResponseDTO;
import com.compassuol.sp.challenge.msproducts.domain.entities.Product;
import com.compassuol.sp.challenge.msproducts.exceptions.NotFound;
import com.compassuol.sp.challenge.msproducts.mapper.ProductMapper;
import com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.compassuol.sp.challenge.msproducts.mapper.ProductMapper.toUpdate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> getAll(){
        List<Product> products = productRepository.findAll();

        return ProductMapper.toListDTO(products);
    }

    @Transactional
    public ProductResponseDTO saveProduct(ProductRequestDTO productDTO) {
        Product product = ProductMapper.toModel(productDTO);
        Product productSaved = productRepository.save(product);

        return ProductMapper.toDTO(productSaved);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()
                -> new NotFound("Product not found"));
        productRepository.deleteById(product.getId());
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new NotFound("Product not found"));

        Product productUpdated = toUpdate(productToUpdate, productDTO);
        Product productResponse = productRepository.save(productUpdated);

        return ProductMapper.toDTO(productResponse);
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFound("Product not found"));

        return ProductMapper.toDTO(product);
    }
}

