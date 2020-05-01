package org.assignment.applause.backend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Devices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Device implements Serializable {

    @Id
    @Column(name = "device_id")
    private Long deviceId;

    private String description;
}
