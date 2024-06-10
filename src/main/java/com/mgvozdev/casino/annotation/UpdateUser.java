package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.dto.UserEditDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
        tags = "users",
        requestBody = @RequestBody(
                description = "UserEditDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UserEditDto.class)
                )
        ),
        responses = {
                @ApiResponse(responseCode = "200", description = "updated"),
                @ApiResponse(responseCode = "400", description = "not updated"),
                @ApiResponse(responseCode = "404", description = "not found")
        }
)
public @interface UpdateUser {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
