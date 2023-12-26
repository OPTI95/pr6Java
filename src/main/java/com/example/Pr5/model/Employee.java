package com.example.Pr5.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "employee")
    private User user;
    @NotBlank(message = "Имя обязательно для заполнения")
    @Size(max = 255, message = "Имя должно содержать не более 255 символов")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    @Size(max = 255, message = "Фамилия должна содержать не более 255 символов")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "Должность обязательна для заполнения")
    @Size(max = 255, message = "Должность должна содержать не более 255 символов")
    @Column(name = "position")
    private String position;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
