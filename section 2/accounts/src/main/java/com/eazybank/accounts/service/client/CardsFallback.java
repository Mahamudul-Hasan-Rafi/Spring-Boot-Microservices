package com.eazybank.accounts.service.client;

import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;
import com.eazybank.accounts.dto.CardsDto;
import com.eazybank.accounts.dto.LoansDto;
import java.util.Collections;
import java.util.List;

@Component
public class CardsFallback implements CardFeignClient{
    @Override
    public ResponseEntity<List<CardsDto>> fetchCardDetails(String correlationId, String mobileNumber) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
