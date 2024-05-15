package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.dto.ChipSetDto;
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
@RequestMapping(method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
@Operation(summary = "adding chips for a certain player",
        description = "if chips are created, the method returns the set of these chips, otherwise throws exception NOT CREATED",
        tags = "chips",
        requestBody = @RequestBody(
                description = "set of ChipSetDtos without amount",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ChipSetDto.class)
                )
        ),
        responses = {
                @ApiResponse(responseCode = "201", description = "created"),
                @ApiResponse(responseCode = "400", description = "not created")
        }
)
public @interface AddChipsForPlayer {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
