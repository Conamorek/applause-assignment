package org.assignment.applause.backend.service;

import org.assignment.applause.backend.repository.DeviceRepository;
import org.assignment.applause.backend.repository.TesterRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class DictionaryService {

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;

    public DictionaryService(TesterRepository testerRepository, DeviceRepository deviceRepository) {
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
    }

    public Map<String, Set<?>> getFindTestersDictionaries() {
        final Map<String, Set<?>> dictionaries = new HashMap<>();
        dictionaries.put("testersCountries", this.testerRepository.loadAllTesterCountry());
        dictionaries.put("devices", this.deviceRepository.findAllDeviceDescriptions());

        return dictionaries;
    }
}
