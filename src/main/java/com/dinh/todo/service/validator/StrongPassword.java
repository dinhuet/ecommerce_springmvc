package com.dinh.todo.service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {

    String message() default "Must be 8 characters long and";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
