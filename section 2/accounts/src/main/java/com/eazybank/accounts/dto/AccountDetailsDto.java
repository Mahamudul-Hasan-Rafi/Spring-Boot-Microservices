package com.eazybank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "account")
@Getter
@Setter
@AllArgsConstructor
public class AccountDetailsDto {
    private String version;
    private Map<String, String> details;
    private Map<String, List<String>> contact;
    private List<String> products;
}

