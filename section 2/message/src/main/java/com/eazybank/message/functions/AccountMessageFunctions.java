package com.eazybank.message.functions;

import com.eazybank.message.dto.AccountMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class AccountMessageFunctions {

    public static final Logger logger = LoggerFactory.getLogger(AccountMessageFunctions.class);


    @Bean
    public Function<AccountMessageDto, AccountMessageDto> input() {
        return accountMessageDto -> {
            logger.info("Processing account message for account number: {}", accountMessageDto.accountNumber());
            // Here you can add the logic to send an email using the details from accountMessageDto
            // For example, you might call an EmailService to send the email

            // Simulating email sending
            System.out.println("Account Mobile Number: " + accountMessageDto.mobileNumber());
            System.out.println("Preparing to send email...");
            System.out.println("Sending email to: " + accountMessageDto.email());
            System.out.println("Email subject: Account Notification");
            System.out.println("Email body: " + accountMessageDto.message());

            logger.info("Email sent successfully to: {}", accountMessageDto.email());
            return accountMessageDto;
        };
    }

}
