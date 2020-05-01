package org.assignment.applause.backend.loaders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Order(2)
@Slf4j
public class DeviceEntityLoader extends AbstractEntityLoader {

    public DeviceEntityLoader(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected void loadEntity() {
        ClassPathResource path = new ClassPathResource("devices.csv");
        String nativeQuery = "INSERT INTO Devices(device_id, description) VALUES(?, ?)";
        SetupData setupData = new SetupData(path, nativeQuery);
        loadEntity(setupData);
    }

    @Override
    protected void setValuesToStatement(PreparedStatement preparedStatement, String[] orderedValues) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(orderedValues[0]));
        preparedStatement.setString(2, orderedValues[1]);
    }
}
