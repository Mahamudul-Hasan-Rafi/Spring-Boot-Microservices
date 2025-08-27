package com.eazybank.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTracingFilter {

    public static final Logger logger = LoggerFactory.getLogger(ResponseTracingFilter.class);
    @Autowired
    FilterUtility filterUtility;

    // Implement filter logic here
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            // Add tracing information to the response headers
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                String correlationId = exchange.getRequest().getHeaders().getFirst("eazybank-correlation-id");
                logger.debug("Updateing the correlation id to the response header: {}", correlationId);
                exchange.getResponse().getHeaders().add("eazybank-correlation-id", correlationId);
            }));
        };
    }
}
