package org.assignment.applause.backend.repository;

import org.assignment.applause.backend.model.TesterExperienceDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class TesterExperienceRepository {

    private final EntityManager entityManager;

    public TesterExperienceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<TesterExperienceDTO> findTesterExperienceBy(Set<String> countries, Set<String> devices) {
        Query query = this.entityManager.createNativeQuery(generateFindTesterExperienceQueryBy(countries, devices));

        if (query.getParameters().stream().anyMatch(param -> param.getName().equals("countries"))) {
            query.setParameter("countries", countries);
        }
        if (query.getParameters().stream().anyMatch(param -> param.getName().equals("devices"))) {
            query.setParameter("devices", devices);
        }

        List<Object[]> queryResult = query.getResultList();
        final List<TesterExperienceDTO> result = new ArrayList<>();
        if (queryResult != null) {
            for (final Object[] r : queryResult) {
                result.add(new TesterExperienceDTO((String) r[0], (String) r[1], (BigInteger) r[2]));
            }
        }
        return result;
    }

    private static String generateFindTesterExperienceQueryBy(Set<String> countries, Set<String> devices) {
        String prefixQueryPart = "select t.first_name, t.last_name, count(t.tester_id) as experience from bugs b " +
                "join devices d on (b.device_id = d.device_id) " +
                "join testers t on (b.tester_id = t.tester_id) ";

        String suffixQueryPart = "group by t.first_name, t.last_name " +
                "order by experience desc ";

        return prefixQueryPart + getWhereClause(countries, devices) + suffixQueryPart;
    }

    private static String getWhereClause(Set<String> countries, Set<String> devices) {
        if (countries.contains("ALL") && devices.contains("ALL")) {
            return "";
        }
        if (!countries.contains("ALL") && !devices.contains("ALL")) {
            return "where t.country in (:countries) AND d.description in (:devices)";
        }

        if (!countries.contains("ALL")) {
            return "where t.country in (:countries) ";
        }

        if (!devices.contains("ALL")) {
            return "where d.description in (:devices)";
        }

        return "";
    }
}
