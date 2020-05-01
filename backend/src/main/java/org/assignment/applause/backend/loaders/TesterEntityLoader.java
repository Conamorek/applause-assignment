package org.assignment.applause.backend.loaders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Order(1)
@Slf4j
public class TesterEntityLoader extends AbstractEntityLoader {

    public TesterEntityLoader(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected void loadEntity() {
        ClassPathResource path = new ClassPathResource("testers.csv");
        String nativeQuery = "INSERT INTO Testers(tester_id, first_name, last_name, country, last_login) VALUES(?, ?, ?, ?, ?)";
        SetupData setupData = new SetupData(path, nativeQuery);
        loadEntity(setupData);
    }

    @Override
    protected void setValuesToStatement(PreparedStatement preparedStatement, String[] orderedValues) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(orderedValues[0]));
        preparedStatement.setString(2, orderedValues[1]);
        preparedStatement.setString(3, orderedValues[2]);
        preparedStatement.setString(4, orderedValues[3]);
        preparedStatement.setString(5, orderedValues[4]);
    }
}
