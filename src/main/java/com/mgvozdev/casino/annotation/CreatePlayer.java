package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
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
@Operation(summary = "creation of a new player",
        description = "if player is created, the method returns its created DTO, otherwise throws exception NOT CREATED",
        tags = "player-controller",
        requestBody = @RequestBody(
                description = "PlayerCreateDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PlayerCreateDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid creation",
                                        value = """
                                                {
                                                    "documentNumber": "98139401",
                                                    "buyIn": 200
                                                }
                                                """),
                                @ExampleObject(
                                        name = "Invalid creation, not existing profile",
                                        value = """
                                                {
                                                    "documentNumber": "1234567",
                                                    "buyIn": 200
                                                }
                                                """),
                                @ExampleObject(
                                        name = "Invalid creation, non-positive buy-in",
                                        value = """
                                                {
                                                    "documentNumber": "98139401",
                                                    "buyIn": -100
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
                                schema = @Schema(implementation = PlayerReadDto.class)
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
public @interface CreatePlayer {
}
