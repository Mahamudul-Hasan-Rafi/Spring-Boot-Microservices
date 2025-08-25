package com.eazybank.loans.service.impl;

import com.eazybank.loans.constants.LoansConstants;
import com.eazybank.loans.dto.LoansDto;
import com.eazybank.loans.entity.Loans;
import com.eazybank.loans.exception.LoansAlreadyExistsException;
import com.eazybank.loans.exception.ResourceNotFoundException;
import com.eazybank.loans.mapper.LoansMapper;
import com.eazybank.loans.repository.LoansRepository;
import com.eazybank.loans.service.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.eazybank.loans.mapper.LoansMapper.mapToLoansDto;

@Service
public class LoansServiceImpl implements ILoansService {
    private final LoansRepository loansRepository;

    @Autowired
    public LoansServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @Override
    public void createLoan(String mobileNumber) {
        Optional<List<Loans>> loans = loansRepository.findByMobileNumber(mobileNumber);

        if (loans.isPresent() && !loans.get().isEmpty()) {
            throw new LoansAlreadyExistsException("Loan already exists for mobile number: " + mobileNumber);
        }

        loansRepository.save(createNewLoan(mobileNumber));

    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        Random random = new Random();
        long randomLoanNumber = 10000000L + random.nextInt(999999);
        System.out.println("Generated random loan number: " + randomLoanNumber);

        char firstChar = (char) (random.nextInt(26) + 'a');
        char secondChar = (char) (random.nextInt(26) + 'A');
        String loanNumberPrefix = "" + firstChar + secondChar;
        String loanNumberSuffix = String.valueOf(randomLoanNumber);

        String loanNumber = loanNumberPrefix + loanNumberSuffix;
        System.out.println("Generated loan number: " + loanNumber);

        newLoan.setLoanNumber(loanNumber);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public List<LoansDto> fetchLoanDetails(String mobileNumber) {
        List<Loans> loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        return loans.stream().map(loan -> mapToLoansDto(loan, new LoansDto())).toList();
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        List<Loans> loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        for (Loans loan : loans) {
            loansRepository.delete(loan);
        }
        return true;
    }
}
