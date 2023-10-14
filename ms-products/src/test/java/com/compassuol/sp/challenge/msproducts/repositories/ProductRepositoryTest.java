package com.compassuol.sp.challenge.msproducts.repositories;

import com.compassuol.sp.challenge.msproducts.domain.entities.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.compassuol.sp.challenge.msproducts.commom.ProductConstants.*;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach() {
        /**
         *  Nos testes de persistencia o PRODUCT acaba
         *  ganhando um id, assim garantimos que ele
         *  sempre tera o id null apos cada teste
         */
        PRODUCT.setId(null);
    }

    @Sql(scripts = "/sql/import_products.sql")
    @Test
    void getAllProductsReturnsProductsList() {
        List<Product> sut = productRepository.findAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(3);
    }

    @Test
    void getAllProductsReturnsEmptyList() {
        assertThatCode(() -> {
            productRepository.findAll();
        }).doesNotThrowAnyException();

        List<Product> sut = productRepository.findAll();
        assertThat(sut).isEmpty();
    }

    @Test
    void createProductWithValidDataReturnProduct() {
        Product product = productRepository.save(PRODUCT);
        Product sut = testEntityManager.find(Product.class, product.getId());

        assertThat(sut).isNotNull();
        assertThat(sut)
                .isEqualToComparingFieldByFieldRecursively(product);
    }

    @Test
    public void createProductWithInvalidDataNullThrowsException() {
        Product emptyProduct = new Product();
        assertThatThrownBy(()->productRepository.save(emptyProduct)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createProductWithInvalidDataEmptyThrowsException() {
        assertThatThrownBy(
                ()->productRepository.save(EMPTY_PRODUCT)
        ).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createProductWithExistentNameThrowsException() {
        Product product = testEntityManager.persistFlushFind(PRODUCT);
        testEntityManager.detach(product);
        product.setId(null);

        assertThatThrownBy(
                ()-> productRepository.save(product)
        ).isInstanceOf(RuntimeException.class);
    }

}
