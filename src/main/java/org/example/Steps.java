package org.example;

import java.util.List;

public class Steps extends Display {

    private List<String> steps;

    public void prepareSteps(String recipe) {//that makes the steps
        int i = 1;
        for(String step : initialProcessing(recipe)) {
            steps.add(String.format(i + ". " + step.trim()));
            i++;
        }
    }

    public List<String> getSteps() {//to get the list with steps
        return steps;
    }


    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String[] initialProcessing(String recipe) {
        return recipe.split("\\R{2,}");
    }

    @Override
    public void display(String recipe) {
        int i = 1;
        System.out.println("Βήματα:");
        for(String step : initialProcessing(recipe)) {
            System.out.println(i + ". " + step.trim());
            i++;
        }
    }


}