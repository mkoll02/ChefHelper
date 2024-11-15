package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.nio.file.*;

public class RecipeReader {
    public static Recipe loadRecipe(String filePath) {
        Recipe recipe = new Recipe();
        // Ανάγνωση και ανάλυση του αρχείου
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Ανάλυση της κάθε γραμμής για υλικά, σκεύη και χρόνο
                // Προσθήκη στο αντικείμενο recipe
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά τη φόρτωση της συνταγής: " + e.getMessage());
        }
        return recipe;
    }

    public static String recipeAsString(String recipeName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(recipeName)));
    }//returns recipe file as string (untested)
}
