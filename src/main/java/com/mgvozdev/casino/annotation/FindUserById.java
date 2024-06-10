package com.mgvozdev.casino.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "user search by his UUID",
        description = "if user is found, the method returns user DTO, otherwise throws exception NOT FOUND",
        tags = "users",
        responses = {
                @ApiResponse(responseCode = "200", description = "found"),
                @ApiResponse(responseCode = "400", description = "not found")
        }
)
public @interface FindUserById {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
