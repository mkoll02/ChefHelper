package org.example;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Παρακαλώ δώστε το αρχείο συνταγής.");
            return;
        }

        String recipeFilePath = args[0];
/*
        Recipe recipe = RecipeLoader.loadRecipe(recipeFilePath);
        recipe.display();


        Recipe recipe = RecipeReader.loadRecipe(recipeFilePath);
        recipe.equals();
*/

        //to test recipeAsString when we add recipe files
        String fullRecipe = RecipeReader.recipeAsString(recipeFilePath);
        System.out.print(fullRecipe);

    }
}