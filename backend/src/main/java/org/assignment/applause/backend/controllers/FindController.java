package org.assignment.applause.backend.controllers;

import org.assignment.applause.backend.model.SearchCriteria;
import org.assignment.applause.backend.model.TesterExperienceDTO;
import org.assignment.applause.backend.service.FindTesterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/find")
@CrossOrigin
public class FindController {

    private final FindTesterService findTesterService;

    public FindController(FindTesterService findTesterService) {
        this.findTesterService = findTesterService;
    }

    @PostMapping
    public List<TesterExperienceDTO> findTesterExperience(@RequestBody SearchCriteria searchCriteria) {
        return this.findTesterService.findTesterExperience(searchCriteria);
    }
}
