package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {
    List<String> ingredients = new ArrayList<>();
    List<Float> quantity = new ArrayList<>();
    List<String> measurement = new ArrayList<>();
    List<Integer> occurrences =  new ArrayList<>();

    List<Integer> numberOfPeople = new ArrayList<>();

    @Override
    public void display(String recipe) {
        System.out.println("Υλικά:");
        printIngredients(recipe);
    }

    public void setNumberOfPeople(List<Integer> numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void printIngredients(String recipe) {
        for(String ingredient : prepareIngredients(recipe)){
            System.out.println(ingredient);
        }
    }

    public List<String> prepareIngredients(String recipe) {
        List<String> initial;
        occurrences = indexes("@", recipe);
        if(!occurrences.isEmpty()) {
            initial = isolateString(recipe, occurrences, "#", "~");
            prepareArrays(recipe);
            return initial;
        }else{
            return "There are no ingredients in the recipe.".lines().toList(); //
        }
    }

    public void prepareArrays(String r) {
        List<String> initial = isolateString(r, occurrences, "#", "~");
    }
}

