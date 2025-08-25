package com.eazybank.loans.service;

import com.eazybank.loans.dto.LoansDto;

import java.util.List;

public interface ILoansService {
    void createLoan(String mobileNumber);

    List<LoansDto> fetchLoanDetails(String mobileNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);
}
