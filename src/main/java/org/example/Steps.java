package org.example;

public class Steps extends Display {

    public String[] prepareSteps(String recipe) {
        return recipe.split("\\R{2,}");
    }

    @Override
    public void display(String recipe) {
        int i = 1;
        System.out.println("Βήματα:");
        for(String step : prepareSteps(recipe)) {
            System.out.println(i + ". " + step.trim());
            i++;
        }
    }
}
