package com.dinh.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Email(message = "Email không hợp lệ")
    @NotBlank
    String email;

    @NotBlank
    @Size(min = 6, message = "Password phải có tối thiểu 6 ký tự")
    String password;

    @NotBlank
    @Size(min = 3, max = 15, message = "FullName phải có tối thiểu 3 ký tự")
    String fullName;

    String address;

    String phone;

    String avatar;

    // roleid
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

    @OneToOne(mappedBy = "user")
    Cart cart;
}
