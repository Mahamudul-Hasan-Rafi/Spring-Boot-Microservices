package com.eazybank.accounts.dto;

public record AccountMessageDto(Long accountNumber, String name, String email, String mobileNumber,
                                String message) {
}
