package com.mgvozdev.casino.validation;

import com.mgvozdev.casino.util.ErrorMessage;
import com.mgvozdev.casino.validation.impl.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberChecker {

    String message() default ErrorMessage.INVALID_PHONE_NUMBER;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
