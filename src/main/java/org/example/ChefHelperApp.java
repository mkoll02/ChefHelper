package org.example;

import java.io.IOException;
import java.util.Scanner;

public class ChefHelperApp {
    public static void main(String[] args) {

        // Ελέγχουμε αν δόθηκε αρχείο ως όρισμα
        if (args.length != 1) {
            System.out.println("Καλώς ήρθατε! \nΕπιλέξτε ανάμεσα στις συνταγές: pancakes, omelette, porridge\n");
            System.out.println("Χρήση στο terminal: java -jar recipes.jar <filename.cook>");
            return;
        }

        // Λήψη του αρχείου που δόθηκε ως όρισμα
        String fileName = args[0];
        try {
            // Δημιουργούμε το RecipeReader και διαβάζουμε το περιεχόμενο της συνταγής
            RecipeReader reader = new RecipeReader(fileName);
            String recipeContent = reader.readRecipe();

            // Δημιουργούμε ένα αντικείμενο Recipe και το επεξεργαζόμαστε
            Recipe recipe = new Recipe(fileName, recipeContent);

//            //άτομα
//            System.out.println("Για πόσα άτομα θέλετε να μαγειρέψετε;\n");
//            Scanner scanner = new Scanner(System.in);
//            int people = scanner.nextInt();
//            recipe.numberOfPeople(people);

            // Εκτύπωση της συνταγής
            Display[] printer = Display.printer();
            for(Display print : printer) {
                print.display();
            }

        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την ανάγνωση της συνταγής: " + e.getMessage());
        }
    }
}