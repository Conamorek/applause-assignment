package org.assignment.applause.backend.controllers;

import org.assignment.applause.backend.service.DictionaryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/dictionary")
@CrossOrigin
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    public Map<String, Set<?>> getFindTestersDictionaries() {
        return this.dictionaryService.getFindTestersDictionaries();
    }
}
