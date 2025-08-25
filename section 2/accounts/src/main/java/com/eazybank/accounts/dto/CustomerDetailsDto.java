package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Schema to hold Customer Account Loan and Card details",
        name = "Customer Details",
        requiredProperties = {"name", "email", "mobileNumber", "accountsDto", "loansDto", "cardsDto"})
public class CustomerDetailsDto {

    @Schema(description = "Customer Name", example = "John Doe")
    private String name;

    @Schema(description = "Customer Email", example = "john@gmail.com")
    private String email;

    @Schema(description = "Customer Mobile Number", example = "1234567890")
    private String mobileNumber;

    @Schema(description = "Account Details")
    private AccountsDto accountsDto;

    @Schema(description = "Loan Details")
    private LoansDto loansDto;

    @Schema(description = "Card Details")
    private CardsDto cardsDto;

}
