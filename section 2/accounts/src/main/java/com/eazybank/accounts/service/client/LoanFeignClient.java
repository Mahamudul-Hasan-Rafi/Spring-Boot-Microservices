package com.eazybank.accounts.service.client;

import com.eazybank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("loans")
public interface LoanFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<List<LoansDto>> fetchLoanDetails(@RequestParam String mobileNumber);
}
