package com.eazybank.accounts.controller;

import com.eazybank.accounts.constants.AccountConstants;
import com.eazybank.accounts.dto.CustomerAccountDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.dto.ErrorResponseDto;
import com.eazybank.accounts.dto.ResponseDto;
import com.eazybank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD ResftAPI for EazyBank",
        description = "This API provides CRUD operations for EazyBank accounts. " +
                "It allows you to create, fetch, update, and delete customer accounts."
)
@RestController
@RequestMapping(path = "/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    IAccountsService iAccountsService;

    @Operation(
            summary = "Create a new customer account",
            description = "This endpoint allows you to create a new customer account. " +
                    "You need to provide the customer's details in the request body."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Request Body"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Account Already Exists"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "Account Created Successfully"
            )
    }
    )
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Account Created Succesfully"));
    }

    @Operation(
            summary = "Fetch customer account details",
            description = "This endpoint allows you to fetch the details of a customer account. " +
                    "You need to provide the customer's mobile number as a query parameter."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Fetched Successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Mobile Number",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account Not Found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerAccountDto> fetchAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        CustomerAccountDto customerAccountDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAccountDto);
    }

    @Operation(
            summary = "Update customer account details",
            description = "This endpoint allows you to update the details of a customer account. " +
                    "You need to provide the updated account details in the request body."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Updated Successfully"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @PutMapping(path="/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerAccountDto customerAccountDto){
        boolean updated = iAccountsService.updateAccount(customerAccountDto);
        if(updated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Account Updated Succesfully"));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto("404", "Account Not Found"));
        }
    }

    @Operation(
            summary = "Delete a customer account",
            description = "This endpoint allows you to delete a customer account. " +
                    "You need to provide the customer's mobile number as a query parameter."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Deleted Successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account Not Found"
            )
    })
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must b 10 digits") String mobileNumber){
        boolean deleted = iAccountsService.deleteAccount(mobileNumber);
        if(deleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Account Deleted Succesfully"));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto("404", "Account Not Found"));
        }
    }

}
