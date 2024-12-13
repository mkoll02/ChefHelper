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
    List<Integer> quantity = new ArrayList<>();;
    List<Integer> occurrences = new ArrayList<>();;

    @Override
    public void display(String recipe) {
        setInitial(prepareInitial(recipe));
        displayIngredients("Υλικά:");
    }

    public void toPrint() {
        checkAndSumDuplicates();
//        multiplyForPeople();
//        conversions();
        setIngredients();
    }

    public void displayIngredients(String whatItPrints) {
        System.out.println(whatItPrints);
        prepareIngredients();
        printIngredients();
    }

    public void printIngredients() {
        toPrint();
        for(String ingredient : ingredients){
            System.out.println(ingredient);
        }
    }

    public void setIngredients() {//name+quantity+measurement
        String a;
        for(int i=0; i<name.size(); i++) {
            a = String.format("%d%s %s", quantity.get(i), measurement.get(i), name.get(i));
            ingredients.add(a);
        }
    }

    public List<String> prepareInitial(String recipe) { //initial string
        occurrences = indexes("@", recipe);
        if(occurrences.isEmpty()) throw new IllegalArgumentException("Δεν υπάρχουν υλικά σε αυτή τη συνταγή.");
        return isolateString(recipe, occurrences, "#", "~");
    }

    public void setInitial(List<String> initial) {
        this.initial = initial;
    }

    public void prepareIngredients() {
        //setInitial(prepareInitial(recipe));
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
        List<Integer> tempQuantity = new ArrayList<>();
        String n, m;
        int q;

        for(int i=0; i<name.size(); i++) {
            n = name.get(i);
            m = measurement.get(i);
            q = quantity.get(i);
            int index = tempName.indexOf(n);
            if(!tempName.contains(n)) {//if it isn't already add it
                tempName.add(n);
                tempQuantity.add(q);
                tempMeasurement.add(m);
            }else{//if it is sum the quantity
                if (index < tempQuantity.size()) tempQuantity.set(index, tempQuantity.get(index) + q); // sum
            }
        }
        addToLists(tempName, tempMeasurement, tempQuantity);
    }

    public void addToLists(List<String> temp_name, List<String> temp_measurement, List<Integer> temp_quantity) {
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

    public void clearLists(String s) {}

    public void conversions() {//

    }

    public void multiplyForPeople() {
        quantity.replaceAll(q -> q * numberOfPeople);
    }
}

