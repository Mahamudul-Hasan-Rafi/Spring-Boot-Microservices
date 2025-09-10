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
    public Function<AccountMessageDto, AccountMessageDto> email() {
        return accountMessageDto -> {
            logger.info("Processing account message for account number: {}", accountMessageDto.accountNumber());
            // Here you can add the logic to send an email using the details from accountMessageDto
            // For example, you might call an EmailService to send the email

            // Simulating email sending
            System.out.println("Sending email to: " + accountMessageDto.email());
            System.out.println("Email subject: Account Notification");
            System.out.println("Email body: " + accountMessageDto.message());

            logger.info("Email sent successfully to: {}", accountMessageDto.email());
            return accountMessageDto;
        };
    }

    @Bean
    public Function<AccountMessageDto, Long> sms() {
        return accountMessageDto -> {
            logger.info("Processing SMS for account number: {}", accountMessageDto.accountNumber());
            // Here you can add the logic to send an SMS using the details from accountMessageDto
            // For example, you might call an SmsService to send the SMS

            // Simulating SMS sending
            System.out.println("Sending SMS to: " + accountMessageDto.name());
            System.out.println("SMS body: " + accountMessageDto.message());

            logger.info("SMS sent successfully to: {}", accountMessageDto.name());
            return accountMessageDto.accountNumber();
        };
    }
}
