package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data @AllArgsConstructor
@Schema(description = "Schema to hold Error Response details",
        name = "Error Response",
        requiredProperties = {"apiPath", "status", "errorMessage", "errorTime"})
public class ErrorResponseDto {

    @Schema(description = "API Path")
    private String apiPath;

    @Schema(description = "HTTP Status")
    private HttpStatus status;

    @Schema(description = "Error Message")
    private String errorMessage;

    @Schema(description = "Error Time")
    private LocalDate errorTime;
}
