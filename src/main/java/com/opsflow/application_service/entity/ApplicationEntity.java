
package com.opsflow.application_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "applications")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Application name cannot be blank")
    @Size(max = 100, message = "Application name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Team cannot be blank")
    @Size(max = 100, message = "Team name cannot exceed 100 characters")
    private String team;

    @NotBlank(message = "Environment cannot be blank")
    @Pattern(
            regexp = "DEV|QA|STAGING|PROD",
            message = "Environment must be DEV, QA, STAGING or PROD"
    )
    private String environment;

    @NotBlank(message = "Version cannot be blank")
    private String version;

    @NotBlank(message = "Status cannot be blank")
    @Pattern(
            regexp = "RUNNING|STOPPED|MAINTENANCE",
            message = "Status must be RUNNING, STOPPED or MAINTENANCE"
    )
    private String status;

    public ApplicationEntity() {
    }

    public ApplicationEntity(String name, String team,
                             String environment, String version,
                             String status) {
        this.name = name;
        this.team = team;
        this.environment = environment;
        this.version = version;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
