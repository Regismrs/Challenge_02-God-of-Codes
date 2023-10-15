package com.compassuol.sp.challenge.msproducts.repositories;

import com.compassuol.sp.challenge.msproducts.domain.entities.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void updateProductWithValidDataReturnsProduct(){
        Product originalProduct = PRODUCT;
        productRepository.save(originalProduct);

        Product updatedProduct = new Product("Produto Atualizado", "Descrição Atualizada", BigDecimal.valueOf(20.0));
        updatedProduct.setId(originalProduct.getId());

        productRepository.save(updatedProduct);

        Optional<Product> retrievedProduct = productRepository.findById(originalProduct.getId());
        assertThat(retrievedProduct).isPresent();

        Product updatedProductFromDatabase = retrievedProduct.get();
        assertThat(updatedProductFromDatabase.getId()).isEqualTo(originalProduct.getId());
        assertThat(updatedProductFromDatabase.getName()).isEqualTo("Produto Atualizado");
        assertThat(updatedProductFromDatabase.getDescription()).isEqualTo("Descrição Atualizada");
        assertThat(updatedProductFromDatabase.getValue()).isEqualByComparingTo(BigDecimal.valueOf(20.0));
    }

    @Test
    public void updateProductWithInvalidDataNullThrowsException() {
        assertThatThrownBy(() -> productRepository.save(EMPTY_PRODUCT_WITH_ID)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void updateProductWithInvalidDataEmptyThrowsException() {
        assertThatThrownBy(
                () -> productRepository.save(EMPTY_PRODUCT_WITH_ID)
        ).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void updateProductWithDuplicateNameThrowsException() {
        Product originalProduct = new Product("Produto Original", "Descrição Original", BigDecimal.valueOf(1.0));
        productRepository.save(originalProduct);

        Product updatedProduct = new Product("Produto Original", "Nova Descrição", BigDecimal.valueOf(2.0));
        updatedProduct.setId(originalProduct.getId() + 1);

        assertThatThrownBy(() -> productRepository.save(updatedProduct))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

}
