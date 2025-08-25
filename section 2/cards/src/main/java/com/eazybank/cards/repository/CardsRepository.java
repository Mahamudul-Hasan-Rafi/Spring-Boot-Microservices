package com.eazybank.cards.repository;

import com.eazybank.cards.entity.Cards;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Long> {
    @Query("SELECT a FROM Cards a WHERE a.mobileNumber = :mobileNumber")
    Optional<List<Cards>> findByMobileNumber(String mobileNumber);

    Optional<Cards> findByCardNumber(@NotEmpty(message = "Card Number can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits") String cardNumber);
    // Additional query methods can be defined here if needed
}
