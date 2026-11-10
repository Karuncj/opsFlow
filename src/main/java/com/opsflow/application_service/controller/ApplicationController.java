
package com.opsflow.application_service.controller;

import com.opsflow.application_service.dto.ApplicationRequest;
import com.opsflow.application_service.dto.ApplicationResponse;
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
    public ResponseEntity<ApplicationResponse> createApplication(
            @Valid @RequestBody ApplicationRequest request) {

        ApplicationResponse response =
                applicationService.createApplication(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public ApplicationResponse updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.updateApplication(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {

        applicationService.deleteApplication(id);

        return ResponseEntity.noContent().build();
    }
}
