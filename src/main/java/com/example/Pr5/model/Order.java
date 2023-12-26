package com.example.Pr5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Order_Table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @NotBlank(message = "Номер заказа обязателен для заполнения")
    @Size(max = 255, message = "Номер заказа должен содержать не более 255 символов")
    @Column(name = "orderNumber")
    private String orderNumber;

    @NotNull(message = "Дата заказа обязательна для заполнения")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "totalAmount")
    private double totalAmount;

    public Order() {
    }

    public Order(Long id, String orderNumber, Date orderDate, double totalAmount) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
