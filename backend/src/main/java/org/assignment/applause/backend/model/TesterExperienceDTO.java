package org.assignment.applause.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesterExperienceDTO {

    private String firstName;
    private String lastName;
    private BigInteger experience;
}
