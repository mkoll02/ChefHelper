package org.example;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Παρακαλώ δώστε το αρχείο συνταγής.");
            return;
        }

        String recipeFilePath = args[0];
        Recipe recipe = RecipeLoader.loadRecipe(recipeFilePath);
        recipe.display();


    }
}