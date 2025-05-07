package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Schema to hold Customer Account details",
        name = "Customer Account",
        requiredProperties = {"name", "email", "mobileNumber", "accountsDto"})
public class CustomerAccountDto {
    @Schema(description = "Customer Name", example = "John Doe")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Schema(description = "Email", example = "eazy@gmail.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Mobile Number", example = "1234567890")
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Accounts details")
    @NotEmpty(message = "Accounts details cannot be empty")
    private AccountsDto accountsDto;
}
