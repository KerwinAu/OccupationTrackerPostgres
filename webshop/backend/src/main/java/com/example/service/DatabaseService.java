package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DatabaseService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void createDatabaseFromFile(String filePath) {
        try {
            String script = readScriptFromFile(filePath);
            System.out.println("Executing script:\n" + script); 
            executeScript(script);
        } catch (IOException e) {
            // Handle file reading exception
            e.printStackTrace();
        }
    }

    private String readScriptFromFile(String scriptPath) throws IOException {
        Path path = Paths.get(scriptPath);
        return new String(Files.readAllBytes(path));
    }

    private void executeScript(String script) {
        Query query = entityManager.createNativeQuery(script);
        query.executeUpdate();
    }
}
