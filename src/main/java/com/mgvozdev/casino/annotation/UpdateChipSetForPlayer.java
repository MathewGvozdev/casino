package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.ChipSetDto;
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
@Operation(summary = "editing of certain chipset for a player",
        description = "if chipset is found and updated, the method returns the list of all player's chips, " +
                      "otherwise throws exception NOT UPDATED",
        tags = {"player-controller", "chips"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the player",
                        required = true,
                        examples = {
                                @ExampleObject(
                                        name = "Player with red chips",
                                        value = "dfafbb82-414b-4dc5-872e-f9dc63b1ee42"
                                )
                        }
                )
        },
        requestBody = @RequestBody(
                description = "ChipSetDto without amount",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ChipSetDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid update",
                                        value = """
                                                {
                                                     "chip" : "RED",
                                                     "total": 200
                                                }
                                                """),
                                @ExampleObject(
                                        name = "Invalid update, wrong values",
                                        value = """
                                                {
                                                     "chip" : "RED",
                                                     "total": -50
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
                                schema = @Schema(implementation = ChipSetDto.class)
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
public @interface UpdateChipSetForPlayer {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
