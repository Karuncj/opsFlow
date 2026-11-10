
package com.opsflow.application_service.service;

import com.opsflow.application_service.dto.ApplicationRequest;
import com.opsflow.application_service.dto.ApplicationResponse;
import com.opsflow.application_service.entity.ApplicationEntity;
import com.opsflow.application_service.exception.ApplicationNotFoundException;
import com.opsflow.application_service.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationResponse createApplication(ApplicationRequest request) {

        ApplicationEntity application = new ApplicationEntity();

        application.setName(request.name());
        application.setTeam(request.team());
        application.setEnvironment(request.environment());
        application.setVersion(request.version());
        application.setStatus(request.status());

        ApplicationEntity saved = applicationRepository.save(application);

        return mapToResponse(saved);
    }

    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ApplicationResponse getApplicationById(Long id) {

        ApplicationEntity application = findApplication(id);

        return mapToResponse(application);
    }

    public ApplicationResponse updateApplication(
            Long id, ApplicationRequest request) {

        ApplicationEntity existing = findApplication(id);

        existing.setName(request.name());
        existing.setTeam(request.team());
        existing.setEnvironment(request.environment());
        existing.setVersion(request.version());
        existing.setStatus(request.status());

        ApplicationEntity updated = applicationRepository.save(existing);

        return mapToResponse(updated);
    }

    public void deleteApplication(Long id) {

        ApplicationEntity existing = findApplication(id);

        applicationRepository.delete(existing);
    }

    private ApplicationEntity findApplication(Long id) {

        return applicationRepository.findById(id)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with id: " + id
                        )
                );
    }

    private ApplicationResponse mapToResponse(ApplicationEntity entity) {

        return new ApplicationResponse(
                entity.getId(),
                entity.getName(),
                entity.getTeam(),
                entity.getEnvironment(),
                entity.getVersion(),
                entity.getStatus()
        );
    }
}
