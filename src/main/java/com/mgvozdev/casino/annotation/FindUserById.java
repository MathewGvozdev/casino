package com.mgvozdev.casino.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
        tags = "user-controller",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the user",
                        required = true,
                        examples = {
                                @ExampleObject(
                                        name = "User with the existing ID",
                                        value = "616deeeb-b47f-4550-95f7-cb31dabd14ea"
                                ),
                                @ExampleObject(
                                        name = "User with the non-existing ID",
                                        value = "616deeeb-b47f-4550-95f7-cb31dabd1000"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "found"),
                @ApiResponse(
                        responseCode = "400",
                        description = "not found")
        }
)
public @interface FindUserById {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
