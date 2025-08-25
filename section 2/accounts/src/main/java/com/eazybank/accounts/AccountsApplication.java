package com.eazybank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(
        value = {com.eazybank.accounts.dto.AccountDetailsDto.class})
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "EazyBank Accounts API",
                version = "1.0",
                description = "API for managing bank accounts",
                contact = @Contact(
                        name = "EazyBank Support",
                        url = "https://eazybank.com/support",
                        email = "tutor@eazybytes.com"
                )
        ),

        externalDocs = @ExternalDocumentation(
                description = "EazyBank API Documentation",
                url = "https://eazybank.com/api-docs"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

    @Bean
    public ApplicationListener<RefreshRemoteApplicationEvent> listener() {
        return event -> {
            System.out.println("Received refresh event: " + event);
        };
    }
}
