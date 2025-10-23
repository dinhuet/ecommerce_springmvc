package com.dinh.todo.models.dto;

import com.dinh.todo.service.validator.RegisterChecked;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RegisterChecked
@Data
@FieldDefaults(level = PRIVATE)
public class RegisterDTO {

    @Size(min = 3, max = 15, message = "FirstName phải có tối thiểu 3 ký tự")
    String firstname;

    String lastname;

    @Email(message = "Email không hợp lệ", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    String email;

    @Size(min = 6, message = "Password phải có tối thiểu 6 ký tự")
    String password;

    String confirmPassword;
}
