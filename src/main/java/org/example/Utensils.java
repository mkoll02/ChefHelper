package org.example;

import java.util.List;

public class Utensils extends Display {
    @Override
    public void display(String recipe) {
        // Εντοπισμός δεικτών του συμβόλου '#'
        List<Integer> indexes = indexes("#", recipe);

        // Εξαγωγή εργαλείων
        List<String> utensils = isolateString(recipe, indexes, "@", "~");

        System.out.println("Σκεύη:");
        for (String utensil : utensils) {
            // Αφαιρεί ',' και '{}'
            String cleanedUtensil = utensil.replaceAll("[,{}]", "").trim();
            System.out.println("- " + cleanedUtensil);
        }
    }
}