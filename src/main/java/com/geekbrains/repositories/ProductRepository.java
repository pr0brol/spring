package com.geekbrains.repositories;

import com.geekbrains.entities.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Milk", new BigDecimal(10.0)));
        products.add(new Product(2L, "Bread", new BigDecimal(5.0)));
        products.add(new Product(3L, "Cheese", new BigDecimal(7.0)));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findOneById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public boolean existById(Long id) {
        return products.stream().anyMatch(p -> p.getId().equals(id));
    }

    public boolean existByTitle(String title) {
        return products.stream().anyMatch(p -> p.getTitle().equals(title));
    }

    public void insert(Product product) {
        if (existById(product.getId())) {
            throw new RuntimeException("Product with id: " + product.getId() + " is already exists");
        }
        if (existByTitle(product.getTitle())) {
            throw new RuntimeException("Product with title: " + product.getTitle() + " is already exists");
        }
        products.add(product);
    }
}