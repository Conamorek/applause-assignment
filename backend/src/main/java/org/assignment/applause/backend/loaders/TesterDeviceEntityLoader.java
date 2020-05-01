package org.assignment.applause.backend.loaders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Order(3)
@Slf4j
public class TesterDeviceEntityLoader extends AbstractEntityLoader {

    public TesterDeviceEntityLoader(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected void loadEntity() {
        ClassPathResource path = new ClassPathResource("tester_device.csv");
        String nativeQuery = "INSERT INTO Tester_Device(tester_id, device_id) VALUES(?, ?)";
        SetupData setupData = new SetupData(path, nativeQuery);
        loadEntity(setupData);
    }

    @Override
    protected void setValuesToStatement(PreparedStatement preparedStatement, String[] orderedValues) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(orderedValues[0]));
        preparedStatement.setLong(2, Long.parseLong(orderedValues[1]));
    }

}
