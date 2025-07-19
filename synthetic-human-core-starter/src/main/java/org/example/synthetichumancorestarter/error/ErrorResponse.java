package org.example.synthetichumancorestarter.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final String timestamp;
    private final String message;
    private final String code;
}
