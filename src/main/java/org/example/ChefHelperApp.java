package org.example;

import swing.RecipeApp;

import javax.swing.*;
import java.util.Arrays;

public class ChefHelperApp {

    public static void main(String[] args) {
        
        //swing
        SwingUtilities.invokeLater(() -> {
            RecipeApp gui = new RecipeApp();
            gui.setVisible(true);
        });
        RecipeManager manager = new RecipeManager();

        // Έλεγχος αν υπάρχουν ορίσματα
        if (args.length < 1) {
            System.out.println("Καλώς ήρθατε!\nΛειτουργίες:\n");
            System.out.println("1. Για να δείτε μια συνταγή --> java -jar recipes.jar <συνταγή.cook>\n");
            System.out.println("2. Για λίστα αγορών --> java -jar recipes.jar -list <συντ1.cook> <συντ2.cook>\n");
            return;
        }

        // Για λίστα αγορών
        if ("-list".equalsIgnoreCase(args[0])) {
            if (args.length < 2) {
                System.err.println("Σφάλμα: Πρέπει να δώσετε τουλάχιστον ένα αρχείο συνταγής.");
                return;
            }
            manager.createShoppingList(Arrays.copyOfRange(args, 1, args.length));
        } else {
            try {
                manager.displayRecipe(args[0]);
            } catch (Exception e) {
                System.err.println("Σφάλμα: " + e.getMessage());
            }
        }
    }
}
