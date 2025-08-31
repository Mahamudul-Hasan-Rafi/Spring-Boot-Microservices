package com.eazybank.accounts.service.client;

import org.springframework.stereotype.Component;
import com.eazybank.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

@Component
public class LoanFallback implements LoanFeignClient{
    @Override
    public ResponseEntity<List<LoansDto>> fetchLoanDetails(String correlationId, String mobileNumber) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
