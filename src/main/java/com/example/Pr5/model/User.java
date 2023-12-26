package com.example.Pr5.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @NotBlank(message = "Имя пользователя обязательно для заполнения")
    @Size(max = 255, message = "Имя пользователя должно содержать не более 255 символов")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный формат email")
    @Size(max = 255, message = "Email должен содержать не более 255 символов")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Пароль обязателен для заполнения")
    @Size(max = 255, message = "Пароль должен содержать не более 255 символов")
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
