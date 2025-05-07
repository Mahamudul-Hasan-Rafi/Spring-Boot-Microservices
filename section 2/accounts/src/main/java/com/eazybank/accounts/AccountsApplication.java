package com.eazybank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
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

}
