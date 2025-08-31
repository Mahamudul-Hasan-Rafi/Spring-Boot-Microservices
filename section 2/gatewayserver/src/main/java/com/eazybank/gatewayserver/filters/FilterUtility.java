package com.eazybank.gatewayserver.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return exchange.mutate().request(
                exchange.getRequest().mutate()
                        .header("eazybank-correlation-id", correlationId)
                        .build()
        ).build();
    }
}
