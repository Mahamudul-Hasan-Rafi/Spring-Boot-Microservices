package com.eazybank.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loan")
public record LoanDetailsDto(String version, Map<String, String> details,
                             Map<String, List<String>> contact, List<String> products) {
}
