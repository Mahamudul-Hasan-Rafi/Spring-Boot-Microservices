package com.eazybank.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "card")
public record CardDetailsDto(String version, Map<String, String> details,
                             Map<String, List<String>> contact, List<String> products) {
}
