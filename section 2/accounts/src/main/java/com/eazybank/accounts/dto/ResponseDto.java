package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(description = "Schema to hold Response details",
        name = "Response",
        requiredProperties = {"statusCode", "statusMsg"})
public class ResponseDto {
    @Schema(description = "Status Code")
    private String statusCode;

    @Schema(description = "Status Message")
    private String statusMsg;
}
