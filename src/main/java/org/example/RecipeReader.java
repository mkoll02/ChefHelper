package org.example;

import java.io.*;
import java.nio.file.*;

public class RecipeReader {

    private final String fileName;

    public RecipeReader(String fileName) {
        this.fileName = fileName;
    }

    public String readRecipe() throws IOException {
        // Ελέγχουμε αν το αρχείο υπάρχει εξωτερικά
        Path externalPath = Paths.get(fileName);
        if (Files.exists(externalPath)) {
            return Files.readString(externalPath);
        }

        // Διαφορετικά, διαβάζουμε το αρχείο από το classpath (resources)
        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (resourceStream == null) {
                throw new IOException("Recipe file not found: " + fileName);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
                return content.toString().trim();
            }
        }
    }
}


