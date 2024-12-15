package org.example;

import java.io.IOException;
import java.util.*;

public class RecipeManager {

    // Εμφάνιση συνταγής
    public void displayRecipe(String fileName) throws IOException {
        RecipeReader reader = new RecipeReader(fileName, fileName);
        String recipeContent = reader.readRecipe();

        // Ρωτάμε τον χρήστη για αριθμό ατόμων
        int people = getNumberOfPeople();

        // Εμφάνιση συνταγής
        System.out.println("==== Συνταγή: " + fileName + " ====");
        Display[] printer = Display.printer();
        for (Display print : printer) {
            print.display(recipeContent);
        }
    }

    public void createShoppingList(String[] fileNames) {
        int people = getNumberOfPeople(); // Λήψη αριθμού ατόμων με έλεγχο
        Map<String, Double> shoppingList = new HashMap<>();
        List<String> bigInitial = new ArrayList<>();

        try {
            Ingredients i = new Ingredients();
            for (String fileName : fileNames) {
                RecipeReader reader = new RecipeReader(fileName, fileName);
                String recipeContent = reader.readRecipe();

                // Προσθέτουμε τα μη επεξεργασμένα υλικά
                //bigInitial.addAll(i.prepareInitial(recipeContent));
                reader.addIngredientsToShoppingList(recipeContent, shoppingList, people);
            }

            // Εμφάνιση της λίστας αγορών ΜΙΑ ΜΟΝΟ ΦΟΡΑ
            System.out.println("Λίστα Αγορών:");
            for (Map.Entry<String, Double> entry : shoppingList.entrySet()) {
                System.out.println("- " + entry.getValue() + " " + entry.getKey());
            }

        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την ανάγνωση συνταγής: " + e.getMessage());
        }
    }


    // Μέθοδος για να λάβουμε τον αριθμό ατόμων με έλεγχο εγκυρότητας
    private int getNumberOfPeople() {
        Scanner scanner = new Scanner(System.in);
        int people;
        do {
          System.out.println("Για πόσα άτομα θέλετε να μαγειρέψετε;\n");
            people = scanner.nextInt();
        }while(people<0);
        return people;
    }
}