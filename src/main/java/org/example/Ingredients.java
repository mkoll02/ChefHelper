package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {

    List<String> ingredients = new ArrayList<>();
    List<Integer> quantity = new ArrayList<>();
    List<String> measurement = new ArrayList<>();

    List<Integer> numberOfPeople = new ArrayList<>();

    public void setNumberOfPeople(List<Integer> numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<String> prepareInitial(String recipe) {
        List<String> initial;
        List<Integer> i = indexes("@", recipe);
        if(i.isEmpty()) {
            System.out.println("There are no ingredients in the recipe.");
        }
        //isolateString(String recipe, List<Integer> indexes, String s1, String s2)
        initial = isolateString(recipe, i, "#", "~");
        return initial;
    }

    @Override
    public void display(String recipe) {
        System.out.println("Υλικά:");
        for(String ingredient : prepareInitial(recipe)){
            System.out.println(ingredient);
        }
    }


}

