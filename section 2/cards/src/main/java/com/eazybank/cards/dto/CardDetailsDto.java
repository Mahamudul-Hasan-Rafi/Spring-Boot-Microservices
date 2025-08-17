package com.eazybank.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "card")
@Getter
@Setter
public class CardDetailsDto {
    private String version;
    private Map<String, String> details;
    private Map<String, List<String>> contact;
    private List<String> products;
}
