package com.eazybank.accounts.controller;

import com.eazybank.accounts.constants.AccountConstants;
import com.eazybank.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces= {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
}
