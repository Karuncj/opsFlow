
package com.opsflow.application_service.controller;

import com.opsflow.application_service.entity.ApplicationEntity;
import com.opsflow.application_service.service.ApplicationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "OpsFlow Application Service is running";
    }

    @PostMapping
    public ResponseEntity<ApplicationEntity> createApplication(
            @Valid @RequestBody ApplicationEntity application) {

        ApplicationEntity savedApplication =
                applicationService.createApplication(application);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedApplication);
    }

    @GetMapping
    public List<ApplicationEntity> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationEntity getApplicationById(
            @PathVariable Long id) {

        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public ApplicationEntity updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationEntity updatedApplication) {

        return applicationService.updateApplication(
                id, updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long id) {

        applicationService.deleteApplication(id);

        return ResponseEntity.noContent().build();
    }
}
