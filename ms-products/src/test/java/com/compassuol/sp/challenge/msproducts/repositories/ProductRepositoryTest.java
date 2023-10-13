package com.compassuol.sp.challenge.msproducts.repositories;

import com.compassuol.sp.challenge.msproducts.models.entities.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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

}
