package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

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
}
