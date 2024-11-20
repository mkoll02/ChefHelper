package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Recipe  {

    List<String> ingredients = new ArrayList<> ();
    List<String> utensils = new ArrayList<> ();
    String time = " ";
    List<String> steps = new ArrayList<> ();

    public Recipe(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.startsWith("@")) {
                        ingredients.add(word.substring(1)); // Αφαιρούμε το @
                    } else if (word.startsWith("#")) {
                        utensils.add(word.substring(1)); // Αφαιρούμε το #
                    } else if (word.startsWith("{") && word.endsWith("}")) {
                        time = word.substring(1, word.length() - 1); // Αφαιρούμε {}
                    } else {
                        steps.add(word); // Θεωρούμε ότι είναι βήμα
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
        }
    }

    //recipe print
    public void displayRecipe() {
    }
}



