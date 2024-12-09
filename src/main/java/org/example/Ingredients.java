package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {
    List<String> ingredients = new ArrayList<>(); //final list that's printed
    int numberOfPeople;

    //lists to process
    List<String> initial = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<String> measurement = new ArrayList<>();
    List<Double> quantity = new ArrayList<>();;
    List<Integer> occurrences = new ArrayList<>();;

    @Override
    public void display(String recipe) {
        System.out.println("Υλικά:");
        printIngredients(recipe);
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setIngredients() {//name+quantity+measurement
        String a;
        for(int i=0; i<initial.size(); i++) {
            a = String.format("%,.2f %s %s", quantity.get(i), measurement.get(i), name.get(i));
            ingredients.add(a);
        }
    }

    public void printIngredients(String recipe) {
        toPrint(recipe);
        for(String ingredient : ingredients){
            System.out.println(ingredient);
        }
    }

    public void toPrint(String recipe) {
        prepareIngredients(recipe);
        setIngredients();
//        checkAndSumDuplicates();
//        conversions();
//        multiplyForPeople();
    }

    public List<String> prepareInitial(String recipe) { //initial string
        occurrences = indexes("@", recipe);
        if(occurrences.isEmpty()) throw new IllegalArgumentException("Δεν υπάρχουν υλικά σε αυτή τη συνταγή.");
        return isolateString(recipe, occurrences, "#", "~");
    }

    public void prepareIngredients(String recipe) {
        initial = prepareInitial(recipe);
        String i;
        for (String s : initial) {
            name.add(extractName(s));
            i = insideBrackets(s);
            quantity.add(extractNumberOf(i));
            measurement.add(extractMeasurement(i));
        }
    }

    public void checkAndSumDuplicates() {}

    public void conversions() {}

    public void multiplyForPeople() {}


    public String extractName(String str) {//string before {
        if(str.isEmpty()) return "";
        return stringIfExists(str, 0, str.indexOf("{"));
    }

    public String insideBrackets(String str) {
        int start = str.indexOf("{");
        int end = str.indexOf("}");
        if(str.equals("{}") || start == -1 || end == -1) return "";
        return stringIfExists(str, start+1, end);
    }

    public Double extractNumberOf(String insideBrackets) { //the number before %
        if(insideBrackets.isEmpty()) return 1.0;
        String s = stringIfExists(insideBrackets, 0, insideBrackets.indexOf("%"));
        return Double.parseDouble(s);
    }

    public String extractMeasurement(String insideBrackets) {//from measurement to end
        if(!insideBrackets.contains("%")) return "";
        return insideBrackets.substring(insideBrackets.indexOf("%")+1);
    }
}

