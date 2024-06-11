package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.ChipSetDto;
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
@RequestMapping(method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
@Operation(summary = "adding chips for a certain player",
        description = "if chips are created, the method returns the set of these chips, otherwise throws exception NOT CREATED",
        tags = {"player-controller", "chips"},
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
                description = "set of ChipSetDtos without amount",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ChipSetDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid creation",
                                        value = """
                                                [
                                                    {
                                                        "chip" : "GREEN",
                                                        "total": 100
                                                    },
                                                    {
                                                        "chip" : "RED",
                                                        "total": 100
                                                    }
                                                ]
                                                """),
                                @ExampleObject(
                                        name = "Invalid creation, wrong content",
                                        value = """
                                                [
                                                    {
                                                        "chip" : "GREEN",
                                                        "total": -100
                                                    },
                                                    {
                                                        "chip" : "NoCHIP",
                                                        "total": 100
                                                    }
                                                ]
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
                                schema = @Schema(implementation = ChipSetDto.class)
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
public @interface AddChipsForPlayer {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
