package com.opsflow.application_service.controller;

import com.opsflow.application_service.entity.ApplicationEntity;
import com.opsflow.application_service.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/hello")
    public String hello() {
        logger.info("Hello endpoint called");
        return "OpsFlow Application Service is running";
    }

    @PostMapping
    public ApplicationEntity createApplication(
            @RequestBody ApplicationEntity application) {

        logger.info("Creating application: {}", application);
        return applicationService.createApplication(application);
    }

    @GetMapping
    public List<ApplicationEntity> getAllApplications() {
        logger.info("Fetching all applications");
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationEntity getApplicationById(
            @PathVariable Long id) {

        logger.info("Fetching application with id: {}", id);
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public ApplicationEntity updateApplication(
            @PathVariable Long id,
            @RequestBody ApplicationEntity updatedApplication) {

        logger.info("Updating application with id: {}", id);
        return applicationService.updateApplication(id, updatedApplication);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(
            @PathVariable Long id) {

        logger.info("Deleting application with id: {}", id);
        applicationService.deleteApplication(id);

        return "Application deleted successfully";
    }
}