package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {
    List<String> ingredients = new ArrayList<>();
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
        for(String ingredient : prepareInitial(recipe)){
            System.out.println(ingredient);
        }
    }

    public List<String> prepareInitial(String recipe) { //initial string
        List<Integer> occurrences = indexes("@", recipe);
        if(!occurrences.isEmpty()) {
            return isolateString(recipe, occurrences, "#", "~");
        }else{
            return "There are no ingredients in the recipe.".lines().toList(); //
        }
    }

    public void prepareIngredients(String r) {//
        List<String> initial = prepareInitial(r);
        
        List<String> name;
        List<Float> quantity;
        List<String> measurement;


    }
}

