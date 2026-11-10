
package com.opsflow.application_service.dto;

public record ApplicationResponse(
        Long id,
        String name,
        String team,
        String environment,
        String version,
        String status
) {}
