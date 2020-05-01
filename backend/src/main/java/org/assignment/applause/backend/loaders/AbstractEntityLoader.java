package org.assignment.applause.backend.loaders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractEntityLoader {

    protected static final String DELIMITER = ",";
    protected static final int BATCH_SIZE = 20;

    protected final SessionFactory hibernateFactory;

    protected AbstractEntityLoader(EntityManagerFactory entityManagerFactory) {
        this.hibernateFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    protected abstract void loadEntity();

    protected abstract void setValuesToStatement(PreparedStatement preparedStatement, String[] orderedValues) throws SQLException;

    protected List<String> loadCsvLines(ClassPathResource resource) {
        List<String> allLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            int lines = 0;
            String line = br.readLine();
            while (line != null) {
                if (lines == 0 || line.isBlank()) {
                    lines++;
                    line = br.readLine();
                    continue;
                }
                line = line.replaceAll("\"", "");
                allLines.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new BeanInitializationException("Unable to load file from resource: " + resource);
        }

        return allLines;
    }

    protected void loadEntity(SetupData setupData) {
        List<String> csvRecordLines = loadCsvLines(setupData.pathToCsvFile);
        Session hibernateSession = hibernateFactory.openSession();
        hibernateSession.doWork(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(setupData.nativeQueryToLoadData)) {
                int i = 1;
                for (String csvRecord : csvRecordLines) {
                    String[] csvValues = csvRecord.split(DELIMITER);
                    setValuesToStatement(preparedStatement, csvValues);
                    preparedStatement.addBatch();
                    if (i % BATCH_SIZE == 0) {
                        preparedStatement.executeBatch();
                    }
                    i++;
                }
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                log.error("An exception occurred during bugs.csv loading", e);
            } finally {
                hibernateSession.close();
            }
        });
    }

    @RequiredArgsConstructor
    protected static class SetupData {
        private final ClassPathResource pathToCsvFile;
        private final String nativeQueryToLoadData;
    }
}
