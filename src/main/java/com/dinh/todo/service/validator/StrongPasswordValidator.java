package com.dinh.todo.service.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // check if string contains at least one digit, one lower case letter, one
        // uppercase letter, one special letter and 8 characters long
        if (value == null) {
            return false;
        }

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";

        return value.matches(pattern);
    }
}
