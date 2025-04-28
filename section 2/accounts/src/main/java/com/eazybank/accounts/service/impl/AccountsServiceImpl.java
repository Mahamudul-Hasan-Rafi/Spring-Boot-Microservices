package com.eazybank.accounts.service.impl;

import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomRepository;
import com.eazybank.accounts.service.IAccountsService;

public class AccountsServiceImpl implements IAccountsService {

    public AccountsRepository accountsRepository;
    public CustomRepository customRepository;

    @Override
    public void createAccount(CustomerDto customerDto){

    }
}
