package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.dto.UserEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
@RequestMapping(method = RequestMethod.PUT)
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "editing user's username and password by his UUID",
        description = "if user is found and updated, the method returns its new DTO, otherwise throws exception NOT UPDATED",
        tags = "user-controller",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the user",
                        required = true,
                        examples = {
                                @ExampleObject(
                                        name = "Existing user",
                                        value = "616deeeb-b47f-4550-95f7-cb31dabd14ea"
                                )
                        }
                )
        },
        requestBody = @RequestBody(
                description = "UserEditDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UserEditDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid update",
                                        value = """
                                                {
                                                    "username" : "updated_user",
                                                    "password": sv10
                                                }
                                                """)
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "updated",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserReadDto.class)
                        )),
                @ApiResponse(
                        responseCode = "400",
                        description = "not updated",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ControllerExceptionHandler.class)
                        )),
                @ApiResponse(
                        responseCode = "404",
                        description = "not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ControllerExceptionHandler.class)
                        ))
        }
)
public @interface UpdateUser {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
