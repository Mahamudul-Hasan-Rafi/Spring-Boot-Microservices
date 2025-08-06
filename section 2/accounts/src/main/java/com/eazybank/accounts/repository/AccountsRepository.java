package com.eazybank.accounts.repository;

import com.eazybank.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    @Query("SELECT a FROM Accounts a WHERE a.customer.mobileNumber = :mobileNumber")
    Optional<List<Accounts>> findByCustomerMobileNumber(String mobileNumber);
}
