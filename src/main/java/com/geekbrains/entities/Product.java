package com.geekbrains.entities;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String title;
    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String firstName, BigDecimal price) {
        this.id = id;
        this.title = firstName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }    

}