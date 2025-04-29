package com.eazybank.accounts.controller;

import com.eazybank.accounts.constants.AccountConstants;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.dto.ResponseDto;
import com.eazybank.accounts.service.IAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces= {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    IAccountsService iAccountsService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Account Created Succesfully"));
    }
}
