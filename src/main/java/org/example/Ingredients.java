package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {
    List<String> ingredients = new ArrayList<>();
    int numberOfPeople;

    //lists to process
    List<String> initial, name, measurement;
    List<Float> quantity;
    List<Integer> occurrences;

    @Override
    public void display(String recipe) {
        System.out.println("Υλικά:");
        printIngredients(recipe);
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setIngredients(List<String> ingredients) {//name+quantity+measurement
        this.ingredients = ingredients;
    }

    public void printIngredients(String recipe) {
        for(String ingredient : prepareInitial(recipe)){
            System.out.println(ingredient);
        }
    }

    public List<String> prepareInitial(String recipe) { //initial string
        occurrences = indexes("@", recipe);
        return isolateString(recipe, occurrences, "#", "~");
    }

    public void prepareIngredients(String recipe) {//combine arrays into one
        initial = prepareInitial(recipe);
//        for(int i=0; i<initial.size(); i++) {
//
//        }
    }

    public String extractName(String str) {//string before {
        if(str.isEmpty()) return "";
        return stringIfExists(str, 0, str.indexOf("{"));
    }

    public String insideBrackets(String str) {
        if(str.equals("{}")) return "";
        return stringIfExists(str, 1, str.indexOf("}"));
    }

    public double extractNumberOf(String insideBrackets) { //the number before %
        if(insideBrackets.isEmpty()) return 1;
        String s = stringIfExists(insideBrackets, 0, insideBrackets.indexOf("%"));
        return Double.parseDouble(s);
    }

    public String extractMeasurement(String insideBrackets) {//from measurement to end
        return insideBrackets.substring(insideBrackets.indexOf("%")+1);
    }
}

