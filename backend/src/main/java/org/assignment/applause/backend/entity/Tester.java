package org.assignment.applause.backend.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Testers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tester implements Serializable {

    @Id
    @Column(name = "tester_id")
    private Long testerId;
    private String firstName;
    private String lastName;
    private String country;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tester_device", joinColumns = {
            @JoinColumn(name = "tester_id")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "device_id")
            })
    private Set<Device> devices = new HashSet<>();

    @OneToMany(mappedBy = "tester", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private Set<Bug> bugs = new HashSet<>();
}
