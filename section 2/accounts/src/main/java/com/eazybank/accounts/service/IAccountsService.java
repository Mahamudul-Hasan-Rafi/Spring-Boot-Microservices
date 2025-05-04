package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerAccountDto;
import com.eazybank.accounts.dto.CustomerDto;

public interface IAccountsService {

    public void createAccount(CustomerDto customerDto);

    CustomerAccountDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerAccountDto customerAccountDto);

    boolean deleteAccount(String mobileNumber);
}
