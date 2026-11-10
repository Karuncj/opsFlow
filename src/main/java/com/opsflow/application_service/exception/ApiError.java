
package com.opsflow.application_service.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        int status,
        String message,
        List<String> errors,
        LocalDateTime timestamp
) {
}
