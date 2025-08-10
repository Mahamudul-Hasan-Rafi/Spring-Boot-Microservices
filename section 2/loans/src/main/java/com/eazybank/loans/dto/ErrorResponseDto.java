package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        description = "Schema to hold Error Response details",
        name = "ErrorResponse",
        requiredProperties = {"apiPath", "errorCode", "errorMessage", "errorTime"})
public class ErrorResponseDto {
    @Schema(description = "API path invoked by client", example = "404")
    private String apiPath;

    @Schema(description = "Error code representing the error happened", example = "Resource not found")
    private HttpStatus errorCode;

    @Schema(description = "Detailed error message", example = "The requested resource could not be found")
    private String errorMessage;

    @Schema(description = "Time when the error occurred", example = "2025-01-01T12:00:00")
    private LocalDateTime errorTime;
}
