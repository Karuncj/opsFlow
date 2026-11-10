
package com.opsflow.application_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ApplicationRequest(

        @NotBlank(message = "Application name cannot be blank")
        @Size(max = 100, message = "Application name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Team cannot be blank")
        @Size(max = 100, message = "Team name cannot exceed 100 characters")
        String team,

        @NotBlank(message = "Environment cannot be blank")
        @Pattern(
                regexp = "DEV|QA|STAGING|PROD",
                message = "Environment must be DEV, QA, STAGING or PROD"
        )
        String environment,

        @NotBlank(message = "Version cannot be blank")
        String version,

        @NotBlank(message = "Status cannot be blank")
        @Pattern(
                regexp = "RUNNING|STOPPED|MAINTENANCE",
                message = "Status must be RUNNING, STOPPED or MAINTENANCE"
        )
        String status

) {}
