package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Schema(
        description = "Schema to hold Loan details",
        name = "Loans",
        requiredProperties = {"mobileNumber", "loanNumber", "loanType", "totalLoan", "amountPaid", "outstandingAmount"}
)
@Data
public class LoansDto {

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(description = "Mobile Number", example = "0123456789")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be empty")
    @Pattern(message = "Loan number must be alphanumeric", regexp = "^[a-zA-Z0-9]{10}")
    @Schema(description = "Loan Number", example = "LN123456")
    @Setter(AccessLevel.NONE)
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty")
    @Schema(description = "Type of Loan", example = "Home Loan")
    private String loanType;

    @Positive(message = "Total loan amount must be positive")
    @Schema(description = "Total Loan Amount", example = "500000")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid must be zero or positive")
    @Schema(description = "Amount Paid", example = "200000")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount must be zero or positive")
    @Schema(description = "Outstanding Amount", example = "300000")
    private int outstandingAmount;

    public void setLoanNumber(String loanNumber) {
        if (loanNumber == null) {
            this.loanNumber = loanNumber;
        }
    }
}
