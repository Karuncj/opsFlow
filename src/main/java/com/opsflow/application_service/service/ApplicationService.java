package com.opsflow.application_service.service;

import com.opsflow.application_service.entity.ApplicationEntity;
import com.opsflow.application_service.exception.ApplicationNotFoundException;
import com.opsflow.application_service.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationEntity createApplication(ApplicationEntity application) {
        return applicationRepository.save(application);
    }

    public List<ApplicationEntity> getAllApplications() {
        return applicationRepository.findAll();
    }

    public ApplicationEntity getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with id: " + id));
    }

    public ApplicationEntity updateApplication(Long id, ApplicationEntity updatedApplication) {

        ApplicationEntity existingApplication = getApplicationById(id);

        existingApplication.setName(updatedApplication.getName());
        existingApplication.setTeam(updatedApplication.getTeam());
        existingApplication.setEnvironment(updatedApplication.getEnvironment());
        existingApplication.setVersion(updatedApplication.getVersion());
        existingApplication.setStatus(updatedApplication.getStatus());

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(Long id) {

        ApplicationEntity existingApplication = getApplicationById(id);

        applicationRepository.delete(existingApplication);
    }
}