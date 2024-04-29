package com.mgvozdev.casino.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET)
@Operation(summary = "searching all players",
        description = "if at least one player is found, the method returns a list of player DTOs," +
                      " otherwise throws exception NOT FOUND",
        tags = "players",
        responses = {
                @ApiResponse(responseCode = "200", description = "found"),
                @ApiResponse(responseCode = "400", description = "not found")
        }
)
public @interface FindAllPlayers {
}
