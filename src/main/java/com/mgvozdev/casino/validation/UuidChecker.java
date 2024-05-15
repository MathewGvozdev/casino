package com.mgvozdev.casino.validation;

import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.validation.impl.UuidValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UuidValidator.class)
public @interface UuidChecker {

    String message() default ErrorMessage.INVALID_UUID;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
