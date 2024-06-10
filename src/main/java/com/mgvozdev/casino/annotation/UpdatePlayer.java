package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
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
@Operation(summary = "editing of existing player by his UUID",
        description = "if player is found and updated, the method returns its new DTO, otherwise throws exception NOT UPDATED",
        tags = "player-controller",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the player",
                        required = true,
                        examples = {
                                @ExampleObject(
                                        name = "Existing player",
                                        value = "dfafbb82-414b-4dc5-872e-f9dc63b1ee42"
                                )
                        }
                )
        },
        requestBody = @RequestBody(
                description = "PlayerEditDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PlayerEditDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid update",
                                        value = """
                                                {
                                                    "buyIn" : "500",
                                                    "closedAt": 2024-03-30T16:30:00
                                                    "avgBet": 25
                                                }
                                                """),
                                @ExampleObject(
                                        name = "Invalid update, wrong values",
                                        value = """
                                                {
                                                    "buyIn" : "-500",
                                                    "closedAt": 2024-03-30
                                                    "avgBet": -20
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
                                schema = @Schema(implementation = PlayerReadDto.class)
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
public @interface UpdatePlayer {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
