package org.example;

import java.io.File;
import java.util.Scanner;

public class ChefHelperApp {
    public static void main(String[] args) {

        // Ελέγχουμε αν δόθηκε αρχείο ως όρισμα
        if (args.length != 1) {
            System.out.println("Χρήση: java -jar recipes.jar <filename.cook>");
            return;
        }

        String filePath = args[0];
        File recipeFile = new File(filePath);

        if (!recipeFile.exists()) {
            System.out.println("Το αρχείο " + filePath + " δεν βρέθηκε.");
            return;
        }

        try {
            // Φορτώνουμε τη συνταγή
            Recipe recipe = RecipeReader.loadRecipe(filePath);
            System.out.println(recipe); // Εκτύπωση της συνταγής
        } catch (Exception e) {
            System.out.println("Σφάλμα κατά την επεξεργασία του αρχείου: " + e.getMessage());
        }
    }
}
