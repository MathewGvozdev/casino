package com.mgvozdev.casino.annotation;

import com.mgvozdev.casino.controller.handler.ControllerExceptionHandler;
import com.mgvozdev.casino.dto.ProfileCreateEditDto;
import com.mgvozdev.casino.dto.ProfileReadDto;
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
@Operation(summary = "editing of existing profile by his UUID",
        description = "if profile is found and updated, the method returns its new DTO, otherwise throws exception NOT UPDATED",
        tags = "profile-controller",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the profile",
                        required = true,
                        examples = {
                                @ExampleObject(
                                        name = "Existing profile",
                                        value = "64158c86-bcd8-4267-bb00-16f9fe82051e"
                                )
                        }
                )
        },
        requestBody = @RequestBody(
                description = "ProfileCreateEditDto",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ProfileCreateEditDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Valid update",
                                        value = """
                                                {
                                                   "documentType": "PASSPORT",
                                                   "country": "USA",
                                                   "documentNumber": "11112222",
                                                   "firstName": "Alla",
                                                   "lastName": "Ivanova",
                                                   "dateOfBirth": "2024-06-10",
                                                   "issueDate": "2024-06-10",
                                                   "expirationDate": "2024-06-10",
                                                   "address": "Moscow",
                                                   "phoneNumber": "2124837412",
                                                   "membershipType": "BRONZE",
                                                   "status": "PERMITTED",
                                                   "totalDeposit": 1000,
                                                   "totalWinnings": 600
                                                 }
                                                """),
                                @ExampleObject(
                                        name = "Invalid update, wrong values",
                                        value = """
                                                {
                                                   "documentType": "document",
                                                   "country": "too many symbols",
                                                   "documentNumber": "11112222",
                                                   "firstName": "Alla",
                                                   "lastName": "Ivanova",
                                                   "dateOfBirth": "2024-06-10",
                                                   "issueDate": "2024-06-10",
                                                   "expirationDate": "2024-06-10",
                                                   "address": "Moscow",
                                                   "phoneNumber": "+248374122",
                                                   "membershipType": "BRONZE",
                                                   "status": "PERMITTED",
                                                   "totalDeposit": -1000,
                                                   "totalWinnings": 600
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
                                schema = @Schema(implementation = ProfileReadDto.class)
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
public @interface UpdateProfile {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
