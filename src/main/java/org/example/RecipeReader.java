package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.IOException;
import java.nio.file.*;

public class RecipeReader {
    public static Recipe loadRecipe(String filePath) throws IOException {
        Recipe recipe = new Recipe();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Επεξεργασία κάθε γραμμής του αρχείου
                System.out.println("Διαβάστηκε: " + line);
                // Προσθήκη στοιχείων στη συνταγή (π.χ. υλικά, οδηγίες)
            }
        }
        return recipe;
    }

//suggestion idk if it works
    public static String recipeAsString(String recipeName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(recipeName)));
    }//returns recipe file as string
}
