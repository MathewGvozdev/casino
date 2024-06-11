package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping(method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
@Operation(summary = "creation of a new user",
        description = "if user is created, the method returns its created DTO, otherwise throws exception NOT CREATED",
        tags = "user-controller",
        requestBody = @RequestBody(
                description = "UserCreateDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UserCreateDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid creation",
                                        value = """
                                                {
                                                   "username": "new_user",
                                                   "password": "123",
                                                   "firstName": "John",
                                                   "lastName": "Brown",
                                                   "shift": "DAY",
                                                   "hiredOn": "2024-06-10",
                                                   "salary": 30
                                                }
                                                """),
                                @ExampleObject(
                                        name = "Invalid creation, wrong content",
                                        value = """
                                                {
                                                   "username": "smth_wrong",
                                                   "password": "-09132",
                                                   "firstName": "John",
                                                   "lastName": "Brown",
                                                   "shift": "shift",
                                                   "hiredOn": "2024-06-10",
                                                   "salary": -30
                                                }
                                                """)
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserReadDto.class)
                        )),
                @ApiResponse(
                        responseCode = "400",
                        description = "not created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ControllerExceptionHandler.class)
                        ))
        }
)
public @interface CreateUser {
}
