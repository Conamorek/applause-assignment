package org.assignment.applause.backend.loaders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Order(4)
@Slf4j
public class BugEntityLoader extends AbstractEntityLoader {

    @Autowired
    public BugEntityLoader(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected void loadEntity() {
        ClassPathResource path = new ClassPathResource("bugs.csv");
        String nativeQuery = "INSERT INTO Bugs(bug_id, device_id, tester_id) VALUES(?, ?, ?)";
        SetupData setupData = new SetupData(path, nativeQuery);
        loadEntity(setupData);
    }

    @Override
    protected void setValuesToStatement(PreparedStatement preparedStatement, String[] orderedValues) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(orderedValues[0]));
        preparedStatement.setLong(2, Long.parseLong(orderedValues[1]));
        preparedStatement.setLong(3, Long.parseLong(orderedValues[2]));
    }
}
