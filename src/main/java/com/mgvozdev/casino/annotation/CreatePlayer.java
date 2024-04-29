package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping(value = "/create",
        method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
@Operation(summary = "creation of a new player",
        description = "if player is created, the method returns its created DTO, otherwise throws exception NOT CREATED",
        tags = "players",
        requestBody = @RequestBody(
                description = "PlayerCreateEditDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PlayerCreateDto.class)
                )
        ),
        responses = {
                @ApiResponse(responseCode = "201", description = "created"),
                @ApiResponse(responseCode = "400", description = "not created")
        }
)
public @interface CreatePlayer {
}
