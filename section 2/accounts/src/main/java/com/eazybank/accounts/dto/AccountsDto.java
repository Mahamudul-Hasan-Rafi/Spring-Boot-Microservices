package com.eazybank.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Schema to hold Accounts details",
        name = "Accounts",
        requiredProperties = {"accountNumber", "accountType", "branchAddress"})
public class AccountsDto {
    @Schema(description = "Account Number")
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account Type", example = "Savings")
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(description = "Branch Address", example = "123 Main St, Springfield")
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
