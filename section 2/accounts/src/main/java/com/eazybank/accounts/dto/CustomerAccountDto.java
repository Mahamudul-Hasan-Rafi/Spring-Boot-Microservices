package com.eazybank.accounts.dto;

import lombok.Data;

@Data
public class CustomerAccountDto {
    private String name;

    private String email;

    private String mobileNumber;

    private AccountsDto accountsDto;
}
