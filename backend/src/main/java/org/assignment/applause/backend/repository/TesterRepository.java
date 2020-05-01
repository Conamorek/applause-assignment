package org.assignment.applause.backend.repository;

import org.assignment.applause.backend.entity.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {

    @Query(value = "select distinct t.country from Tester t")
    Set<String> loadAllTesterCountry();
}
