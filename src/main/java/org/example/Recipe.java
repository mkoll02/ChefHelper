package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recipe  {

    List<String> ingredients = new ArrayList<> ();
    List<String> utensils = new ArrayList<> ();
    int totalTime = 0;
    List<String> steps = new ArrayList<> ();

    public Recipe(String name, String content) {
        // Αποθήκευση του ονόματος της συνταγής
        parseContent(content);
    }

    private void parseContent(String content) {
        String[] sections = content.split("\\n\\n");
        for (String section : sections) {
            if (section.contains("@")) extractIngredients(section);
            if (section.contains("#")) extractUtensils(section);
            if (section.contains("~")) extractTime(section);
            steps.add(section.trim());
        }
    }

    private void extractIngredients(String section) {
        Matcher matcher = Pattern.compile("@(\\w +\\s*\\w*)\\{([^}]*)}").matcher(section); // Σωστό
        while (matcher.find()) {
            ingredients.add(matcher.group(1) + " (" + matcher.group(2) + ")");
        }
    }

    private void extractUtensils(String section) {
        Matcher matcher = Pattern.compile("#(\\w+\\s*\\w*)\\{?}?").matcher(section); // Σωστό
        while (matcher.find()) {
            utensils.add(matcher.group(1));
        }
    }

    private void extractTime(String section) {
        Matcher matcher = Pattern.compile("~\\{(\\d+)}").matcher(section); // Σωστό
        while (matcher.find()) {
            totalTime += Long.parseLong(matcher.group(1));
        }
    }

    public void printIngredients() {
        System.out.println("Υλικά:");
        ingredients.forEach(ingredient -> System.out.println("- " + ingredient));
    }

    public void printUtensils() {
        System.out.println("Σκεύη:");
        utensils.forEach(utensil -> System.out.println("- " + utensil));
    }

    public void printSteps() {
        System.out.println("Βήματα:");
        int i=0;

        while ( i <= steps.size()) {

            System.out.printf("%d. %s " , i+1, Arrays.toString(steps.get(i).split("\\n")));
            i++;
        }
    }

    public void printTotalTime() {
        System.out.println("Συνολικός Χρόνος: " + totalTime + " minutes");
    }

    //method για πόσα άτομα είναι η συνταγή & λειτουργίες
    public void numberOfPeople(int people) {
    }

}
