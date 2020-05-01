package org.assignment.applause.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bugs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bug implements Serializable {

    @Id
    private Long bugId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tester_id")
    private Tester tester;

    @JoinColumn(name = "device_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Device device;
}
