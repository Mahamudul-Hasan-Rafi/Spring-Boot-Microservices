package com.eazybank.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "account")
public record AccountDetailsDto(String version, Map<String, String> details,
                                Map<String, List<String>> contact, List<String> products) {
}

