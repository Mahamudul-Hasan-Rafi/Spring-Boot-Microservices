package com.eazybank.accounts.repository;

import com.eazybank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByMobileNumber(String mobileNumber);
}
