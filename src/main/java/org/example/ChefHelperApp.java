package org.example;

public class ChefHelperApp {
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();

        // Έλεγχος ορισμάτων
        if (args.length < 1) {
            System.out.println("Καλώς ήρθατε!\n Λειτουργίες:");
            System.out.println("1. Για να δείτε μια συνταγή --> java -jar recipes.jar <συνταγή.cook>\n");
            System.out.println("2. Για την λίστα αγορών σας --> java -jar recipes.jar -list <συντ1.cook> <συντ2.cook>\n");
            return;
        }

        // Επεξεργασία επιλογών
        if ("-list".equalsIgnoreCase(args[0])) {
            if (args.length < 2) {
                System.err.println("Σφάλμα: Πρέπει να δώσετε τουλάχιστον ένα αρχείο συνταγής.");
                return;
            }
            // Κλήση της μεθόδου για δημιουργία λίστας αγορών
            manager.createShoppingList(java.util.Arrays.copyOfRange(args, 1, args.length));
        } else {
            try {
                // Κλήση της μεθόδου για εμφάνιση μίας συνταγής
                manager.displayRecipe(args[0]);
            } catch (Exception e) {
                System.err.println("Σφάλμα: " + e.getMessage());
            }
        }
    }
}
