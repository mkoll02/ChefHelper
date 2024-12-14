package org.example;

import java.io.IOException;
import java.util.*;

public class RecipeManager {

    // Εμφάνιση μίας συνταγής
    public void displayRecipe(String fileName) throws IOException {
        RecipeReader reader = new RecipeReader(fileName, fileName);
        String recipeContent = reader.readRecipe();

        // Ρωτάμε τον χρήστη για αριθμό ατόμων
        System.out.println("Για πόσα άτομα θέλετε να μαγειρέψετε;");
        Scanner scanner = new Scanner(System.in);
        int people = scanner.nextInt();

        // Εμφάνιση συνταγής
        System.out.println("==== Συνταγή: " + fileName + " ====");
        Display[] printer = Display.printer();
        for (Display print : printer) {
            print.display(recipeContent);
        }
    }

    // Δημιουργία λίστας αγορών για πολλές συνταγές
    public void createShoppingList(String[] fileNames) {
        System.out.println("Για πόσα άτομα θέλετε να μαγειρέψετε;");
        Scanner scanner = new Scanner(System.in);
        int people = scanner.nextInt();

        List<String> bigInitial = new ArrayList<>();
        Ingredients i = new Ingredients();

        Map<String, Double> shoppingList = new HashMap<>();
        try {
            for (String fileName : fileNames) {
                RecipeReader reader = new RecipeReader(fileName, fileName);
                String recipeContent = reader.readRecipe();
                bigInitial.addAll(i.prepareInitial(recipeContent)); //add all unprocessed ingredients of all recipes together in a list
                reader.addIngredientsToShoppingList(recipeContent, shoppingList, people);
            }
            // Εκτύπωση λίστας αγορών
            i.displayList(bigInitial);
            //System.out.println("Λίστα Αγορών:");
            for (Map.Entry<String, Double> entry : shoppingList.entrySet()) {
                System.out.println("- " + entry.getValue() + " " + entry.getKey());
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την ανάγνωση συνταγής: " + e.getMessage());
        }
    }
}
