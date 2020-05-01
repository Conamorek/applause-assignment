package org.assignment.applause.backend.service;

import org.assignment.applause.backend.model.TesterExperienceDTO;
import org.assignment.applause.backend.model.SearchCriteria;
import org.assignment.applause.backend.repository.TesterExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTesterService {
    private final TesterExperienceRepository testerExperienceRepository;

    public FindTesterService(TesterExperienceRepository testerExperienceRepository) {
        this.testerExperienceRepository = testerExperienceRepository;
    }

    public List<TesterExperienceDTO> findTesterExperience(SearchCriteria searchCriteria) {
        return this.testerExperienceRepository.findTesterExperienceBy(searchCriteria.getCountries(), searchCriteria.getDevices());
    }
}
