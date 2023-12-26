package com.example.Pr5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;


@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    @NotBlank(message = "Имя обязательно для заполнения")
    @Size(max = 255, message = "Имя должно содержать не более 255 символов")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    @Size(max = 255, message = "Фамилия должна содержать не более 255 символов")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "Email обязателен для заполнения")
    @Size(max = 255, message = "Email должен содержать не более 255 символов")
    @Column(name = "email")
    private String email;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
