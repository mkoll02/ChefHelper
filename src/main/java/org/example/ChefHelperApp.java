package org.example;

import java.io.IOException;

public class ChefHelperApp {
    public static void main(String[] args) {

        // Ελέγχουμε αν δόθηκε αρχείο ως όρισμα
        if (args.length != 1) {
            System.out.println("Καλώς ήρθατε! \nΕπιλέξτε ανάμεσα στις συνταγές: pancakes\n");
            System.out.println("Χρήση στο terminal: java -jar recipes.jar <filename.cook>");
            return;
        }

        //entry point
        String fileName = args[0];
        try {
            // Δημιουργούμε το RecipeReader και διαβάζουμε τη συνταγή
            RecipeReader reader = new RecipeReader(fileName);
            String recipeContent = reader.readRecipe();
            System.out.println("Recipe content:");
            System.out.println(recipeContent);
        } catch (IOException e) {
            System.err.println("Error reading recipe: " + e.getMessage());
        }
    }
}
