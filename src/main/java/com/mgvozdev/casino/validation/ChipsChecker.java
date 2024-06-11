package com.mgvozdev.casino.validation;

import com.mgvozdev.casino.util.ErrorMessage;
import com.mgvozdev.casino.validation.impl.ChipsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChipsValidator.class)
public @interface ChipsChecker {

    String message() default ErrorMessage.INVALID_CHIPS;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
