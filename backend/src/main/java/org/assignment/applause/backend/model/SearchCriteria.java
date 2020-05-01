package org.assignment.applause.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SearchCriteria {
    private Set<String> countries;
    private Set<String> devices;
}
