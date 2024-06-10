package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.ProfileCreateEditDto;
import com.mgvozdev.casino.dto.ProfileReadDto;
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
@Operation(summary = "creation of a new profile",
        description = "if profile is created, the method returns its created DTO, otherwise throws exception NOT CREATED",
        tags = "profile-controller",
        requestBody = @RequestBody(
                description = "ProfileCreateEditDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ProfileCreateEditDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid creation",
                                        value = """
                                                {
                                                   "documentType": "PASSPORT",
                                                   "country": "USA",
                                                   "documentNumber": "91849102",
                                                   "firstName": "John",
                                                   "lastName": "Johnson",
                                                   "dateOfBirth": "1990-06-10",
                                                   "issueDate": "2023-06-01",
                                                   "expirationDate": "2027-06-01",
                                                   "address": "New York",
                                                   "phoneNumber": "+1472347819",
                                                   "membershipType": "BRONZE",
                                                   "status": "PERMITTED",
                                                   "totalDeposit": 0,
                                                   "totalWinnings": 0
                                                 }
                                                """),
                                @ExampleObject(
                                        name = "Invalid creation",
                                        value = """
                                                {
                                                   "documentType": "no document",
                                                   "country": "too many symbols",
                                                   "documentNumber": "91849102",
                                                   "firstName": "John",
                                                   "lastName": "Johnson",
                                                   "dateOfBirth": "1990-06-10",
                                                   "issueDate": "2023-06-01",
                                                   "expirationDate": "2027-06-01",
                                                   "address": "New York",
                                                   "phoneNumber": "+7819",
                                                   "membershipType": "BRONZE",
                                                   "status": "PERMITTED",
                                                   "totalDeposit": 0,
                                                   "totalWinnings": 0
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
                                schema = @Schema(implementation = ProfileReadDto.class)
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
public @interface CreateProfile {
}
