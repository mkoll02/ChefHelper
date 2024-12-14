package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {
    List<String> ingredients = new ArrayList<>(); //final list that's printed
    int numberOfPeople;

    //lists to process
    List<String> initial = new ArrayList<>();
    List<Integer> occurrences = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<String> measurement = new ArrayList<>();
    List<Double> quantity = new ArrayList<>();

    @Override
    public void display(String recipe) { //for one recipe
        setInitial(prepareInitial(recipe));
        displayIngredients("Υλικά:");
    }

    public void displayList(List<String> i) {//for multiple recipes
        setInitial(i);
        displayIngredients("Λίστα αγορών:");
    }

    public void displayIngredients(String whatItPrints) {// -list of ingredients
        System.out.println(whatItPrints);
        prepareIngredients();
        printIngredients();
    }

    public void printIngredients() {
        toPrint();
        for(String ingredient : ingredients){
            System.out.println(ingredient);
        }
        clearLists("all lists that process");
    }

    public void toPrint() {//call methods that process lists
        checkAndSumDuplicates();
        //multiplyForPeople();
        conversions();
        setIngredients();
    }

    public void setIngredients() {//name+quantity+measurement
        String a;
        for(int i=0; i<name.size(); i++) {
            a = String.format("%.0f %s %s", quantity.get(i), measurement.get(i), name.get(i));
            ingredients.add(a);
        }
    }

    public List<String> prepareInitial(String recipe) { //initial unprocessed string
        occurrences = indexes("@", recipe);
        if(occurrences.isEmpty()) throw new IllegalArgumentException("Δεν υπάρχουν υλικά σε αυτή τη συνταγή.");
        return isolateString(recipe, occurrences, "#", "~");
    }

    public void setInitial(List<String> initial) {
        this.initial = initial;
    }

    public void prepareIngredients() {//extract what's necessary for each list
        String i;
        for (String s : initial) {
            name.add(extractName(s));
            i = insideBrackets(s);
            quantity.add(extractNumberOf(i));
            measurement.add(extractMeasurement(i));
        }
    }

    public void checkAndSumDuplicates() {
        List<String> tempName = new ArrayList<>();
        List<String> tempMeasurement = new ArrayList<>();
        List<Double> tempQuantity = new ArrayList<>();
        String n, m;
        Double q;

        for(int i=0; i<name.size(); i++) {
            //create temporary lists to add unique elements
            n = name.get(i);
            m = measurement.get(i);
            q = quantity.get(i);

            int index = tempName.indexOf(n);
            if(index == -1) {//if it isn't already add it
                tempName.add(n);
                tempQuantity.add(q);
                tempMeasurement.add(m);
            }else{//if it is sum the quantity
                if (index < tempQuantity.size()) tempQuantity.set(index, tempQuantity.get(index) + q); // sum with what's already in list
            }
        }
        addToLists(tempName, tempMeasurement, tempQuantity);
    }

    public void addToLists(List<String> temp_name, List<String> temp_measurement, List<Double> temp_quantity) {
        clearLists();
        name.addAll(temp_name);
        quantity.addAll(temp_quantity);
        measurement.addAll(temp_measurement);
    }

    public void clearLists() {
        name.clear();
        measurement.clear();
        quantity.clear();
    }

    public void clearLists(String s) {
        clearLists();
        occurrences.clear();
        initial.clear();
    }

    public void conversions() {//convert if necessary
        for(int i=0; i<quantity.size(); i++) {
            convertTo("ml", "liters", i);
            convertTo("gr", "kg", i);
        }
    }

    public void convertTo(String s1, String s2, int i) {
        double k;
        if(quantity.get(i) >= 1000) {
            if (measurement.get(i).equals(s1)) {
                measurement.set(i, s2);
                k = quantity.get(i) / 1000;
                quantity.set(i, k);
            }
        }
    }

    public void multiplyForPeople() {
        quantity.replaceAll(q -> q * numberOfPeople);
    }
}

