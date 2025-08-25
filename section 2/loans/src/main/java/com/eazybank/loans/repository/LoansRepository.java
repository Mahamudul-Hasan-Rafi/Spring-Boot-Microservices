package com.eazybank.loans.repository;

import com.eazybank.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans, Long> {

    Optional<List<Loans>> findByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);
}
