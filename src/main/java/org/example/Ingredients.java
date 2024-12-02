package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {

    public List<String> prepareIngredients(String recipe) {
        List<String> ingredients;
        List<Integer> i = indexes("@", recipe);
        if(i.isEmpty()) {
            System.out.println("There are no ingredients in the recipe.");
        }
        //isolateString(String recipe, List<Integer> indexes, String s1, String s2)
        ingredients = isolateString(recipe, i, "#", "~");
        return ingredients;
    }

    @Override
    public void display(String recipe) {
        System.out.println("Υλικά:");
        for(String ingredient : prepareIngredients(recipe)){
            System.out.println(ingredient);
        }
    }


}

