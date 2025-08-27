package com.eazybank.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTracingFilter implements GlobalFilter {
    public static final Logger logger = LoggerFactory.getLogger(RequestTracingFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(httpHeaders)) {
            logger.info("Correlation ID found in the request headers: {}. ", httpHeaders.get("eazybank-correlation-id"));
        } else {
            String correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            logger.info("Generated new Correlation ID: {} for the request.", correlationId);
        }
        return chain.filter(exchange);

    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders httpHeaders) {
        String correlationId = httpHeaders.getFirst("eazybank-correlation-id");
        return (correlationId != null && !correlationId.isEmpty());
    }
}
