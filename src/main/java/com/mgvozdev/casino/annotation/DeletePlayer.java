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
@RequestMapping(method = RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
@Operation(summary = "player deleting by his UUID",
        description = "the method returns ResponseEntity with the result of operation",
        tags = "player-controller",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the player",
                        required = true,
                        examples = {
                                @ExampleObject(
                                        name = "Deleting existing ID",
                                        value = "dfafbb82-414b-4dc5-872e-f9dc63b1ee42"
                                ),
                                @ExampleObject(
                                        name = "Deleting non-existing ID",
                                        value = "dfafbb82-414b-4dc5-872e-f9dc63b1ee00"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "deleted, no content"),
                @ApiResponse(
                        responseCode = "404",
                        description = "not found")
        }
)
public @interface DeletePlayer {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
