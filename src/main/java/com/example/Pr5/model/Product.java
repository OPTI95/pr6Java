package com.example.Pr5.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;


@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @NotBlank(message = "Название продукта обязательно для заполнения")
    @Size(max = 255, message = "Название продукта должно содержать не более 255 символов")
    @Column(name = "name")
    private String name;

    @Size(max = 255, message = "Описание продукта должно содержать не более 255 символов")
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    public Product() {
    }

    public Product(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
