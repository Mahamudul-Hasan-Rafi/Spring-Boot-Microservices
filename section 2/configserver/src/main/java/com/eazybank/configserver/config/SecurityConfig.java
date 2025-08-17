package com.eazybank.configserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {
                })  // Enable HTTP Basic Auth with lambda
                .csrf(csrf -> csrf.disable())  // Disable CSRF with lambda
                .formLogin(formLogin -> formLogin.disable());  // Disable form login with lambda

        return http.build();
    }
}
