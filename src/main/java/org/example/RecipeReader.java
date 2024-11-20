package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

public class RecipeReader {
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
  public static Recipe loadRecipe(String filePath) throws IOException {
    Recipe recipe = new Recipe();
  try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    String line;
  while ((line = reader.readLine()) != null) {
 //Επεξεργασία κάθε γραμμής του αρχείου
    System.out.println("Διαβάστηκε: " + line);
 //Προσθήκη στοιχείων στη συνταγή (π.χ. υλικά, οδηγίες)
}
}
return recipe;
}
suggestion
  public static String recipeAsString(String recipeName) throws IOException {
    return new String(Files.readAllBytes(Paths.get(recipeName)));
}//returns recipe file as string
}

