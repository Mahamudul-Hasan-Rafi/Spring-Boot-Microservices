package com.eazybank.accounts.service.client;

import com.eazybank.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("cards")
public interface CardFeignClient {
    // Define methods to call the Cards service endpoints
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<List<CardsDto>> fetchCardDetails(@RequestParam
                                                    String mobileNumber);
}
