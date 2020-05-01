package org.assignment.applause.backend.repository;

import org.assignment.applause.backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("select distinct d.description from Device d")
    Set<String> findAllDeviceDescriptions();
}
